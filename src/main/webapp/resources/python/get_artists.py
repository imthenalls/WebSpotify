import spotipy
from spotipy.oauth2 import SpotifyClientCredentials
import pymysql.cursors
import requests
from bs4 import BeautifulSoup
import re
import time 
import math
import random
 
headers = { 'User-Agent': 'Mozilla/5.0 (Windows NT 6.0; WOW64; rv:24.0) Gecko/20100101 Firefox/24.0' } #allow us to be seen a browser

def search_artist(artist_name):
	results = sp.search(q='artist:' + artist_name, type='artist')
	items = results['artists']['items']
	if len(items) > 0:
	    artist = items[0] # take the top result
	    return(artist)

def get_albums(artist,data):
	#create a dictionary 
	add_to = []
	results = sp.artist_albums(artist['uri'], album_type='album')
	albums = results['items']
	num_albums = 0
	ALBUM_CAP = 3
	while results['next']:
	    results = sp.next(results)
	    albums.extend(results['items'])
	for a in albums:
		if(num_albums == ALBUM_CAP):
			break
		else:
			add_to.append(a['id'])
			num_albums = num_albums + 1
	data[artist['id']] =  add_to


def add_songs(album, artist_id, album_id):
	duration = 0
	tracks = sp.album_tracks(album) 
	for track in tracks['items']:
		duration = duration + (track['duration_ms']/1000)
		add_to.append(track)
		#add the song to the db
	return duration

def get_song_count(popularity): 
	low = (int(math.floor(popularity/10.0)) * 10) * 100
	high = low + 1000
	return(random.randint(low,high)) 

client_credentials_manager = SpotifyClientCredentials('b49b47c61c1a42eba548b8308a10c1da', '3b7777a1dfd64aa6b6f6eaa3f0a9f410')
sp = spotipy.Spotify(client_credentials_manager=client_credentials_manager)
sp.trace=False

artist_names = ["Bruno Mars"]
artists = []
data = {}
#get the artists
for i in range(0, artist_names.__len__()):
	artists.append(search_artist(artist_names[i]))

#get albums from each artist
for artist in artists:
	(get_albums(artist, data))

DEF_SONG = "/resources/audio/kryptonite.mp3"
#connect to the database
connection = pymysql.connect(host='mysql2.cs.stonybrook.edu',
                             user='teamon3',
                             password='KevinLovesTyping',
                             db='teamon3',
                             charset='utf8mb4',
                            cursorclass=pymysql.cursors.DictCursor)

get_artist_id = "SELECT max(`artistId`) from `artists`"
with connection.cursor() as cursor:
	#key is the artist URI 
	for key in data: 
		#add the artist
		q1 = 'INSERT INTO `artists`(`artistName`, `artistId`, `popularity`, `payoutPerPlay`, `imagePath`, `username`, `totalRoyalties`)VALUES(%s, %s, %s, %s, %s, %s, %s)'
		#add_user = 'INSERT INTO `users`(`username`, `password`, `email`)VALUES(%s, %s, %s)'
		artist = sp.artist(key)
		username = artist['name'].replace(" ", "")
		cursor.execute(q1, (artist['name'], 0, artist['popularity'], 0, artist['images'][0]['url'], username, 0))
		cursor.execute(get_artist_id)
		artist_id =  cursor.fetchone()['max(`artistId`)']#get the current artist_id
		#add the genres
		for genre in artist['genres']:
			#check if the genre exists
			check_genre = "SELECT genreId from genres where genre =" +"'" + genre + "'"
			if cursor.execute(check_genre) == 0:
				try:
					#insert the genre into the genre table
					genre_insert = "INSERT INTO `genres`(`genreId`, `genre`)VALUES(%s, %s)"
					x = cursor.execute(genre_insert, (0, genre))
				except: 
					print("genre exists")
		#insert the album into the artistgenre table
			artistgenre_insert = "INSERT INTO `artistgenres`(`artistGenresId`, `artistId`, `genreId`, `genre`)VALUES(%s, %s, %s, %s)"
			get_genreid = "SELECT `genreId` from `genres` where genre = " +"'"+ genre + "'"
			cursor.execute(get_genreid)
			genre_id = cursor.fetchone()['genreId']
			cursor.execute(artistgenre_insert, (0, artist_id, genre_id, genre))

		albums = data[key]
		for album in albums:
			a = sp.album(album)
			q2 = 'INSERT INTO `albums`(`albumId`, `albumname`, `artistId`, `duration`,popularity, `imagePath`)VALUES(%s, %s, %s, %s, %s, %s)'
			cursor.execute(q2,(0, a['name'], artist_id, 0, 0, a['images'][0]['url']))
			get_album_id = "SELECT max(`albumId`) from `albums`"
			cursor.execute(get_album_id)
			album_id = cursor.fetchone()['max(`albumId`)']
			#add the album, set duration to 0
			tracks = sp.album_tracks(album)
			album_duration = 0
			for track in tracks['items']: 
				total_plays = get_song_count(int(artist['popularity']))
				duration = track['duration_ms']/1000
				album_duration += int(duration)
				add_song = "INSERT INTO `songs`(`songId`, `title`, `albumId`, `artistId`, `audioPath`, `mimeType`, `duration`, `totalPlays`, `numFollowers`, `royaltyPerPlay`,`unpayedPlays`, `remove`) VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
				cursor.execute(add_song, (0, track['name'], album_id, artist_id, DEF_SONG, ".mp3", duration, total_plays, 0, 0, 0, 0))
				#save the lyrics
				artist_name = (artist['name'].lower()).replace(" ", "")
				track_name = (track['name'].lower()).replace(" ", "")
				get_song_id = "SELECT max(`songId`) from `songs`"
				cursor.execute(get_song_id)
				song_id = cursor.fetchone()['max(`songId`)']
				time.sleep(1)
				#save_lyrics(track_name, artist_name, song_id)
				
			#update duration after the songs are added
			q3 = "UPDATE `albums` SET `duration` =" + str(album_duration) + " WHERE `albumId` =" + str(album_id)
			cursor.execute(q3)
connection.commit()

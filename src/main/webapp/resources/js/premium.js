$(document).ready(function(){
    console.log('Card Test');
    $('#upgradeForm').card({
        // a selector or DOM element for the container
        // where you want the card to appear
        container: '#cardWrapper', // *required*
        debug: true
        // all of the other options from above
    });
});


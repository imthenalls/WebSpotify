
package com.team0n3.webspotify.dao;

import java.util.List;
import com.team0n3.webspotify.model.RoyaltyPayment;

public interface RoyaltyPaymentDAO {  
  public void addRoyaltyPayment(RoyaltyPayment payment);
  public RoyaltyPayment getRoyaltyPayment(int paymentId);
  public List<RoyaltyPayment> listRoyaltyPayments();
  public void deleteRoyaltyPayment(RoyaltyPayment payment);
  public void updateRoyaltyPayment(RoyaltyPayment payment);
}

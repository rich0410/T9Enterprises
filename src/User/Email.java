package User;

import Email_Setup.sender;

/**
 * This class is using the sender class to send email using threading
 * Two emails are sent one for confirmation and other is for cancellation
 * @author Prabdeep Singh Pannu
 *
 * */
public class Email {

    public Email(){

    }

    public void email_Thread(final String email){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sender sender = new sender(
                            "",           // Insert the data email address password
                            "");

                    sender.sendMail("Confirmation email" , "An appointment has been booked"
                                      ,
                            "", email);

                } catch (Exception e) {
                  System.out.print(e.getMessage());
                }

            }

        }).start();
    }
    public void email_Thread_Cancel(final String email){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sender sender = new sender(
                            "",
                            "");      // Insert the data email address password

                    sender.sendMail("Cancellation email" , "An appointment has been Cancelled"
                            ,
                            "", email);

                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }

            }

        }).start();
    }


}


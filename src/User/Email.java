package User;

import Email_Setup.sender;

/**
 * This class is used to send email to the professor and students about the appointment
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
                            "homemanagement001@gmail.com",
                            "8437839094");

                    sender.sendMail("Confirmation email" , "An appointment has been booked"
                                      ,
                            "homemanagement001@gmail.com", email);

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
                            "homemanagement001@gmail.com",
                            "8437839094");

                    sender.sendMail("Cancellation email" , "An appointment has been Cancelled"
                            ,
                            "homemanagement001@gmail.com", email);

                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }

            }

        }).start();
    }


}


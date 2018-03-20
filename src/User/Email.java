package User;

import Email_Setup.sender_class;

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
                    sender_class sender = new sender_class("NO reply mail",
                            "password");

                    sender.sendMail("Confirmation email" , "An appointment is booked"
                                      ,
                            "NO reply mail", email);

                } catch (Exception e) {
                  System.out.print(e.getStackTrace());
                }

            }

        }).start();
    }


}


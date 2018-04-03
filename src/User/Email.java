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
                    sender_class sender = new sender_class(
                            "homemanagement001@gmail.com",
                            "8437839094");

                    sender.sendMail("Confirmation email" , "An appointment has been booked on"
                                      ,
                            "homemanagement001@gmail.com", email);

                } catch (Exception e) {
                  System.out.print(e.getMessage());
                }

            }

        }).start();
    }


}


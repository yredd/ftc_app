package com.qualcomm.ftcrobotcontroller.Krakens;

import com.qualcomm.ftcrobotcontroller.Krakens.KrakenTelementry;

public class KrakenAuto2 extends KrakenTelementry

{

    public KrakenAuto2()

    {

    } // PushBotAuto

    //--------------------------------------------------------------------------
    //
    // start
    //
    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     *
     * The system calls this member once when the OpMode is enabled.
     */
    @Override public void start ()

    {
        //
        // Call the PushBotHardware (super/base class) start method.
        //
        super.start ();

        //
        // Reset the motor encoders on the drive wheels.
        //
        reset_drive_encoders ();

    } // start

    @Override public void loop ()

    {

        switch (v_state)
        {
        case 0:

            reset_drive_encoders ();

            v_state++;


        case 1:

            run_using_encoders ();

            //
            // Start the drive wheel motors at full power.
            //
            set_drive_power (0.25f, 0.25f);

            //
            // Have the motor shafts turned the required amount?
            //
            // If they haven't, then the op-mode remains in this state (i.e this
            // block will be executed the next time this method is called).
            //
            if (have_drive_encoders_reached (677.4, 677.4))//1329
            {
                //
                // Reset the encoders to ensure they are at a known good value.
                //
                reset_drive_encoders ();

                //
                // Stop the motors.
                //
                set_drive_power (0.0f, 0.0f);

                //
                // Transition to the next state when this method is called
                // again.
                //
                v_state++;
            }
            break;
        //
        // Wait...
        //
        case 2:
            if (have_drive_encoders_reset ())
            {
                v_state++;
            }
            break;
        //
        // Turn left until the encoders exceed the specified values.
        //
        case 3:
            run_using_encoders ();
            set_drive_power (0.25f, -0.25f);
            if (have_drive_encoders_reached (1050, 1050))
            {
                reset_drive_encoders ();
                set_drive_power (0.0f, 0.0f);
                v_state++;
            }
            break;
        //
        // Wait...
        //
        case 4:
            if (have_drive_encoders_reset ())
            {
                v_state++;
            }
            break;
        //
        // Turn right until the encoders exceed the specified values.
        //
        case 5:
            run_using_encoders ();
            set_drive_power (0.75f, 0.75f);
            if (have_drive_encoders_reached (8500, 8500))
            {
                reset_drive_encoders ();
                set_drive_power (0.0f, 0.0f);
                v_state++;
            }
            break;
        //
        // Wait...
        //
        case 6:
            if (have_drive_encoders_reset ())
            {
                v_state++;
            }
            break;

            case 7:
                run_using_encoders ();
                set_drive_power (0.25f, -0.25f);
                if (have_drive_encoders_reached (1100, 1100))
                {
                    reset_drive_encoders ();
                    set_drive_power (0.0f, 0.0f);
                    v_state++;
                }
                break;
            //
            // Wait...
            //
            case 8:
                if (have_drive_encoders_reset ())
                {
                    v_state++;
                }
                break;

            case 9:
                run_using_encoders ();
                set_drive_power (0.50f, 0.50f);
                if (have_drive_encoders_reached (1100, 1100))
                {
                    reset_drive_encoders ();
                    set_drive_power (0.0f, 0.0f);
                    v_state++;
                }
                break;
            //
            // Wait...
            //
            case 10:
                if (have_drive_encoders_reset ())
                {
                    v_state++;
                }
                break;
        //
        // Perform no action - stay in this case until the OpMode is stopped.
        // This method will still be called regardless of the state machine.
        //
        default:
            //
            // The autonomous actions have been accomplished (i.e. the state has
            // transitioned into its final state.
            //
            break;
        }

        //
        // Send telemetry data to the driver station.
        //
        update_telemetry (); // Update common telemetry
        telemetry.addData ("18", "State: " + v_state);

    } // loop

    //--------------------------------------------------------------------------
    //
    // v_state
    //
    /**
     * This class member remembers which state is currently active.  When the
     * start method is called, the state will be initialized (0).  When the loop
     * starts, the state will change from initialize to state_1.  When state_1
     * actions are complete, the state will change to state_2.  This implements
     * a state machine for the loop method.
     */
    private int v_state = 0;

} // PushBotAuto

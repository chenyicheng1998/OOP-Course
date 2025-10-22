public class Television {
    private boolean isOn;
    private int channel;
    private final int MAX_CHANNEL = 10;

    public Television() {
        this.isOn = false;
        this.channel = 1;
    }

    public boolean isOn() {
        return isOn;
    }

    public void pressOnOff() {
        isOn = !isOn;
        if (isOn) {
            System.out.println("TV is now ON");
        } else {
            System.out.println("TV is now OFF");
        }
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        if (isOn) {
            if (channel > MAX_CHANNEL) {
                this.channel = 1;
            } else if (channel < 1) {
                this.channel = MAX_CHANNEL;
            } else {
                this.channel = channel;
            }
        }
    }
}
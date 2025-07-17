package com.lld.practise.designPattern.Structural;
// Step 1: Define the common interface
interface VideoService {
    void playVideo(String videoId);
}

// Step 2: Real Service that is expensive or sensitive
 class RealVideoService implements VideoService {
    @Override
    public void playVideo(String videoId) {
        System.out.println("Streaming video: " + videoId);
    }
}

// Step 3: Proxy that controls access
 class VideoServiceProxy implements VideoService {
    private RealVideoService realService;
    private final boolean isPremiumUser;

    public VideoServiceProxy(boolean isPremiumUser) {
        this.isPremiumUser = isPremiumUser;
    }

    @Override
    public void playVideo(String videoId) {
        if (videoId.startsWith("premium-")) {
            if (!isPremiumUser) {
                System.out.println("Access denied: Upgrade to premium to watch this video.");
                return;
            }
        }

        if (realService == null) {
            realService = new RealVideoService(); // lazy load
        }

        realService.playVideo(videoId);
    }
}

// Step 4: Client code
class  Main{
public static void main(String[] args) {
    VideoService normalUser = new VideoServiceProxy(false);
    VideoService premiumUser = new VideoServiceProxy(true);

    normalUser.playVideo("free-funny-cats");        // ✅ Allowed
    normalUser.playVideo("premium-episode-5");      // ❌ Blocked

    premiumUser.playVideo("premium-episode-5");     // ✅ Allowed
}
}


//class A{
//    public void check(){
//        System.out.println("A proxy bypassed");
//    }
//}
//class proxyA extends A{
//    public A a;
//    public proxyA(A a){
//        this.a=a;
//    }
//    @Override
//    public void check(){
//        if user is valid
//                super.check();
//        else
//            exception
//    }
//}
abstract class Content { //Abstract class: Doesn't know the implementation
  void showVideo();

  void showImage();
}

class VideoContent extends Content { //Knows the implementation of abstract class methods
  @override
  void showImage() {
    print("Image is showing");
  }

  @override
  void showVideo() {
    print("Video is showing");
  }

  void nowShow(){

  }
}

//Content is an abstract class. It can not be initiate I mean no object can be created from it

main() {
  Content content = VideoContent(); //VideoContent is a Content,
  content.showVideo();
  content.showImage();
}
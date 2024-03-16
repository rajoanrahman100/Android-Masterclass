class Car {
  void stop() {
    print("Car Stop");
  }
  void start() {
    print("Car Start");
  }
}

class Tesla implements Car{
  @override
  void start() {

    print("Tesla Start");
  }

  @override
  void stop() {
    print("Tesla Stop");
  }

}

main(){
  Car car=Tesla();
  car.stop();
  car.start();
}
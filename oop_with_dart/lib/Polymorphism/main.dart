import 'package:oop_with_dart/Polymorphism/animal.dart';
import 'package:oop_with_dart/Polymorphism/dog.dart';

main() {
  Dog dog = Dog();
  dog.eat();

  Animal animal = Animal();
  animal.eat();
}
//Advantages of using Polymorphism
// Subclasses can override the behavior of the parent class.
// It allows us to write code that is more flexible and reusable.

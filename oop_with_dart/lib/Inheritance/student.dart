import 'package:oop_with_dart/Inheritance/people.dart';

class Student extends People{
  int? rollNo;
  void showStudentInfo(){
    super.showPeopleInfo(); //Accessing the parent class method using super keyword
    print("\nRoll No is $rollNo");
  }
}
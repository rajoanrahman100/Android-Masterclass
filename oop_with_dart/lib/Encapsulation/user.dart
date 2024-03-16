class User {
  String? _name; //Encapsulate the name variable though "_" private
  int? _age; //Encapsulate the name variable though "_" private

  /// Now in order to get the value of name from outside of this class we need to use the getter function
  String? get getName => _name;

  /// Now in order to set the value of name from outside of this class we need to use the setter function
  void setName(String name) {
    _name = name;
  }

  int get getAge => _age!;

  set setAge(int age) {
    _age = age;
  }

  void displayData(){
    print("Name is $getName and age is $getAge");
  }
}

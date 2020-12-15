class User {
  int id;
  final String username;
  final String firstName;
  final String lastName;
  final String email;
  final String password;
  final String postalCode;
  final String street;
  final String number;
  final String city;
  final String country;

  User({
    this.id,
    this.username,
    this.firstName,
    this.lastName,
    this.email,
    this.password,
    this.postalCode,
    this.street,
    this.number,
    this.city,
    this.country
  });

  factory User.fromJson(Map<String, dynamic> json) => User(
        id: json['id'],
        username: json['username'],
        firstName: json['firstName'],
        lastName: json['lastName'],
        email: json['email'],
        password: json['password'],
        postalCode: json['postalCode'],
        street: json['street'],
        number: json['number'],
        city: json['city'],
        country: json['country']
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "username":   username,
        "firstName":  firstName,
        "lastName":   lastName,
        "email":      email,
        "password":   password,
        "postalCode": postalCode,
        "street":     street,
        "number":     number,
        "city":       city,
        "country":    country
      };

  @override
  String toString() {
    return 'User{id: $id, username: $username, firstName: $firstName, lastName: $lastName, email: $email, password: $password, postalCode: $postalCode, street: $street, number: $number, city: $city, country: $country}';
  }
}


import 'package:app/models/exception_response.dart';
import 'package:app/models/user.dart';

class UserRepsponse{
  User user;
  ExceptionResponse exceptionResponse;

  UserRepsponse(this.user, this.exceptionResponse);

  @override
  String toString() {
    return 'UserRepsponse{user: $user, exceptionResponse: $exceptionResponse}';
  }
}

import 'package:app/models/exception_response.dart';
import 'package:app/models/user.dart';

class UserResponse{
  User user;
  ExceptionResponse exceptionResponse;

  UserResponse(this.user, this.exceptionResponse);

  @override
  String toString() {
    return 'UserRepsponse{user: $user, exceptionResponse: $exceptionResponse}';
  }
}

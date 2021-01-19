class ExceptionResponse {
  final String message;
  final DateTime timestamp;
  final int status;


  ExceptionResponse({this.message, this.timestamp, this.status});


  factory ExceptionResponse.fromJson(Map<String, dynamic> json) => ExceptionResponse(
      message:    json['message'],
      status:     json['status'],
      timestamp:  DateTime.parse(json['timestamp'].toString().split("[")[0])
  );

  Map<String, dynamic> toJson() => {
    "message":    message,
    "status":     status,
    "timestamp":  timestamp
  };

  @override
  String toString() {
    return 'ExceptionResponse{message: $message, timestamp: $timestamp, status: $status}';
  }
}

import 'package:flutter/material.dart';
import 'package:woche3_app2/models/user.dart';
import 'package:provider/provider.dart';
import 'package:woche3_app2/models/users.dart';
import 'package:woche3_app2/screens/create_user_screen.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final List<User> users = context.watch<Users>().users;
    return Scaffold(
      appBar: AppBar(
        title: Text("Home"),
      ),
      body: () {
        if (users != null) {
          return ListView.separated(
            padding: const EdgeInsets.symmetric(vertical: 8),
            itemCount: users.length,
            itemBuilder: (context, index) {
              return ListTile(
                leading: Icon(
                  Icons.account_circle,
                  size: 64,
                ),
                title: Text(users[index].name),
                subtitle: Text(users[index].phone),
              );
            },
            separatorBuilder: (context, index) {
              return Divider();
            },
          );
        }

        return Center(child: CircularProgressIndicator());
      }(),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => CreateUserScreen(),
            ),
          );
        },
        child: Icon(Icons.add),
      ),
    );
  }
}

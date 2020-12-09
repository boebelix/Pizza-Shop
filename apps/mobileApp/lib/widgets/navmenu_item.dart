import 'package:flutter/material.dart';
import 'package:app/screens/order_overview_screen.dart';
import 'package:app/screens/order_screen.dart';
import 'package:app/screens/profile_screen.dart';


class NavMenuItem extends StatelessWidget {
  final String headlineTxt;
  final int position;

  NavMenuItem({this.position, this.headlineTxt});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        // TODO Change to route: it is only for presentation purpose

        switch (position) {
          case 1:
            {
              print("Container Order clicked");
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => OrderScreen()),
              );
            }
            break;

          case 2:
            {
              print("Container OrderOverview clicked");
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => OrderOverviewScreen()),
              );
            }
            break;

          case 3:
            {
              print("Container Profil clicked");
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => ProfileScreen()),
              );
            }
            break;
        }
      },
      child: Container(
        width: double.infinity,
        //color: Colors.orangeAccent,

        child: Stack(
          fit: StackFit.expand,
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Opacity(
                opacity: 0.5,
                child: Card(
                  semanticContainer: true,
                  clipBehavior: Clip.antiAliasWithSaveLayer,
                  child:
                      /*Image.asset(
                    'images/logo.png',
                    height: 60.0,
                    fit: BoxFit.cover,
                  ),*/
                      Container(
                    color: Colors.orangeAccent,
                  ),
                  /*Image.network(
                    'https://placeimg.com/640/480/any',
                    fit: BoxFit.fill,
                  ),*/
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10.0),
                  ),
                  elevation: 13,
                  margin: EdgeInsets.all(10),
                ),
              ),
            ),
            Center(
              child: Text(
                headlineTxt,
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

# WelcomeHome
A system for WelcomeHome to manage donations, inventory, clients, orders, volunteers, and deliveries, supporting refugees and asylum seekers.
## Team Member:
- Yicheng Zhai (yz11708)
- Yixin Jiang (yj3051)
## Language & Database
- Java 11
- MYSQL
## Frameworks & Tools
- Spring Boot
- MyBatis
- Lombok
- JWT
## Features
1. Login & User Session Handling
2. Find Single Item
3. Find Order Items
4. Accept Donation
5. Start an order
6. Add to current order (shopping)
7. User’s tasks
8. Category Rank System
### Login & User Session Handling
Verify userName and password (hashed)
```
SELECT * FROM person WHERE userName = #{userName} and password = #{password}
```
### Find Single Item
Search locations of all pieces of an item by `itemID`
```
SELECT p.pieceNum, p.pDescription, p.roomNum, p.shelfNum, l.shelfDescription 
FROM piece p 
JOIN location l ON p.roomNum = l.roomNum AND p.shelfNum = l.shelfNum 
WHERE p.ItemID = #{itemID}
```
### Find Order Items
Search list of the items, along with the locations of all pieces of each item by `orderID`
```
SELECT i.ItemID, i.iDescription, p.pieceNum, p.pDescription, p.roomNum, p.shelfNum, l.shelfDescription 
FROM ordered o 
JOIN itemIn ii ON o.orderID = ii.orderID
JOIN item i ON ii.ItemID = i.itemID
JOIN piece p ON i.ItemID = p.ItemID 
JOIN location l ON p.roomNum = l.roomNum AND p.shelfNum = l.shelfNum 
WHERE o.orderID = #{orderID}
```
### Accept Donation
Check that the user is a staff member
```
SELECT COUNT(*)
FROM Act 
JOIN Role ON Act.roleID = Role.roleID 
WHERE Act.userName = #{userName} AND Role.rDescription = 'staff'
```
Check that the user is a registered donor 
```
SELECT COUNT(*) 
FROM Act 
JOIN Role ON Act.roleID = Role.roleID 
WHERE Act.userName = #{userName} AND Role.rDescription = 'donor'
```
Insert a new item
```
INSERT INTO Item (itemid, iDescription, color, isnew, haspieces, material, maincategory, subcategory) 
VALUES (#{itemID}, #{iDescription}, #{color}, #{isNew}, #{hasPieces}, #{material}, #{mainCategory}, #{subCategory})
```
Insert new donor info
```
INSERT INTO donatedby(itemid, username, donatedate)
VALUES (#{itemID}, #{userName}, #{donateDate})
```
Insert a new piece
```
INSERT INTO piece(itemID, pieceNum, pDescription, length, width, height, roomNum, shelfNum, pNotes)
VALUES (#{itemID}, #{pieceNum}, #{pDescription}, #{length}, #{width}, #{height}, #{roomNum}, #{shelfNum}, #{pNotes})
```
Check if an `itemID` is unique
```
SELECT COUNT(*) 
FROM Item 
WHERE Item.itemID = #{id}
```
Check if a category is existed
```
SELECT COUNT(*) 
FROM category 
WHERE category.mainCategory = #{mainCategory} AND category.subCategory = #{subCategory}
```
Check if a location is existed
```
SELECT COUNT(*) 
FROM location 
WHERE location.roomNum = #{roomNum} AND location.shelfNum = #{shelfNum}
```
Insert a new category
```
INSERT INTO category(mainCategory, subCategory)
VALUES (#{mainCategory}, #{subCategory})
```
Insert a new location
```
INSERT INTO location(roomNum, shelfNum)
VALUES (#{roomNum}, #{shelfNum})
```
Get the newest `itemID`
```
SELECT COALESCE(MAX(Item.itemID), 0) FROM Item
```
### Start an order
Check that the user is a staff member
```
SELECT COUNT(*)
FROM Act 
JOIN Role ON Act.roleID = Role.roleID 
WHERE Act.userName = #{userName} AND Role.rDescription = 'staff'
```
Check if the username is valid
```
SELECT COUNT(*) 
FROM Act 
JOIN Role ON Act.roleID = Role.roleID 
WHERE Act.userName = #{userName} AND Role.rDescription = 'client'
```
Get the newest `orderID`
```
SELECT COALESCE(MAX(Ordered.orderID), 0) FROM Ordered
```
### Add to current order (shopping)
Get all existing categories
```
SELECT DISTINCT mainCategory, subCategory 
FROM Category
```
Get all items(not already ordered) belonging to a given category
```
SELECT Item.ItemID, Item.iDescription, Item.photo, Item.color, Item.isNew, Item.hasPieces, Item.material 
FROM Item 
WHERE Item.MainCategory = #{mainCategory} AND Item.SubCategory = #{subCategory}
AND Item.ItemID NOT IN (SELECT ItemIn.ItemID FROM ItemIn)
```
Insert a new order
```
INSERT INTO Ordered (orderID, orderDate, orderNotes, supervisor, client)
VALUES (#{orderID}, #{orderDate}, #{orderNotes}, #{supervisor}, #{client})
```
Insert a new (item in order)
```
INSERT INTO Itemin (itemID, orderID, found) 
VALUES (#{itemID}, #{orderID}, #{found})
```
### User’s tasks
Search all orders related to user
```
SELECT o.orderID, o.orderDate, o.supervisor, o.client, i.itemID, i.iDescription, d.username AS donor, d.donateDate, del.userName as deliveredBy, del.date as deliverDate, del.status 
FROM Ordered o 
LEFT JOIN ItemIn ii ON o.orderID = ii.orderID 
LEFT JOIN Item i ON ii.itemID = i.itemID 
LEFT JOIN Donatedby d ON i.itemID = d.itemID 
LEFT JOIN Delivered del ON o.orderID = del.orderID 
WHERE #{userName} IN (o.supervisor, o.client, d.username, del.userName)
```
### Category Rank System
Search categories having the most number orders in a given time period
```
SELECT i.mainCategory,i.subCategory,COUNT(DISTINCT o.orderID) AS orderCount 
FROM Ordered o 
JOIN ItemIn ii ON o.orderID = ii.orderID 
JOIN Item i ON ii.itemID = i.itemID 
WHERE o.orderDate BETWEEN #{startDate} AND #{endDate} 
GROUP BY i.mainCategory, i.subCategory 
ORDER BY orderCount DESC
```

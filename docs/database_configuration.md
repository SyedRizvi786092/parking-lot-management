# Database Configuration

Follow the steps below to configure the database properly:
1. Make sure you have installed MySQL installer and Connector/J ([download link](https://dev.mysql.com/downloads/)).
2. Create a database *'agents'*, and create a table *'youngemployees'* in it, with two columns *'username'* and *'password'* of VARCHAR types. Put atleast one entry in the table. This will be used for login purpose.
3. Create another database *'appdata'*, and create two tables *'slot'* and *'vehicle'* in it, with the following columns:
- **slot**: Create four columns *'floorno'*(INT type) for floor number, *'slotno'*(INT type) for slot number, *'stype'*(VARCHAR type) for type of slot i.e. car, bike or truck, and *'vin'*(VARCHAR type) for storing vehicle identification number of the vehicle parked.
- Put atleast 25 entries in the table for proper visualization of slots while using the app.
- **vehicle**: Create five columns *'vin'*(VARCHAR type), *'vtype'*(VARCHAR type), *'vcolor'*(VARCHAR type), *'status'*(TINYINT type), and *'ticket_id'*(VARCHAR type).
- **Do not put any entry in this table.**
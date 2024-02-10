from flask import Flask, jsonify, request
from flask_cors import CORS
from flask_mysqldb import MySQL



app = Flask(__name__)
CORS(app)


app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''
app.config['MYSQL_DB'] = 'finalproject'
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_PORT'] = 3306
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = True



mysql = MySQL(app)
@app.route('/user')
def users():
    conn = None
    cursor = None
    try:
        conn = mysql.connection
        cur=conn.cursor()
        cur.execute("SELECT * FROM users")
        userRows = cur.fetchall()

        
        response = jsonify(userRows)
        response.status_code = 200
        return response
    except Exception as e:
        print(e)
        return jsonify({'error': str(e)}), 500


@app.route('/add',methods=['POST'])   
def add_user():
    try:
          _json = request.json
          _userid= _json['user_id']      
          _username=_json['user_name']   
          _pass=_json['password']

          if _userid and _username and _pass and request.method=='POST':
              conn=mysql.connection
              cur=conn.cursor()
              sqlQuery="INSERT INTO users Values(%s,%s,%s)"
              bindData=(_userid,_username,_pass)
              cur.execute(sqlQuery,bindData)
              conn.commit()
              response=jsonify(result='User added Successfully')
              response.status_code=200
              return response
          else:
              return showMessage()
    except Exception as e:
            print(e)

            
@app.route('/update_user', methods=['PUT'])
def update_user():
    _json = request.json
    _userid = _json.get('user_id')
    _pass = _json.get('password')  
    if not _userid or not _pass:
        return jsonify({'success': False, 'message': 'Missing user ID or password'}), 400
    try:
        conn = mysql.connection
        cur = conn.cursor()

        cur.execute("SELECT * FROM users WHERE user_id = %s", [_userid])
        user = cur.fetchone()
        if not user:
            return jsonify({'success': False, 'message': 'User not found'}), 404

        sqlQuery = "UPDATE users SET password = %s WHERE user_id = %s"
        cur.execute(sqlQuery, (_pass, _userid))
        conn.commit()

        if cur.rowcount:
            return jsonify({'success': True, 'message': 'Password updated successfully'}), 200
        else:
            return jsonify({'success': False, 'message': 'Update failed'}), 500
    except Exception as e:
        print(e)
        return jsonify({'error': str(e)}), 500



@app.route('/delete/<int:user_id>', methods=['DELETE'])
def delete_user(user_id):
    try:
        conn = mysql.connection
        cur = conn.cursor()
        
        
        cur.execute("SELECT * FROM users WHERE user_id = %s", (user_id,))
        if cur.fetchone() is None:
            return jsonify({'success': False, 'message': 'User not found'}), 404
        
       
        cur.execute("DELETE FROM users WHERE user_id = %s", (user_id,))
        conn.commit()
        
      
        if cur.rowcount == 0:
            return jsonify({'success': False, 'message': 'Deletion failed'}), 500
        
        return jsonify({'success': True, 'message': 'User deleted successfully!'}), 200
    except Exception as e:
        print(e)
        return jsonify({'error': str(e)}), 500


@app.route('/marker')
def markers():
    conn = None
    cursor = None
    try:
        conn = mysql.connection
        cur=conn.cursor()
        cur.execute("SELECT * FROM markers")
        markerRows = cur.fetchall()
       
        response = jsonify(markerRows)
        response.status_code = 200
        return response
    except Exception as e:
        print(e)
        return jsonify({'error': str(e)}), 500

@app.route('/login', methods=['POST'])
def login_user():
    _json = request.json
    _userid = _json['user_id']
    _password = _json['password']

    if _userid and _password:
        conn = mysql.connection
        cur=conn.cursor()
        cur.execute("SELECT user_name FROM users WHERE user_id = %s AND password = %s", (_userid, _password))
        user = cur.fetchone()

        if user:
            return jsonify({'success': True, 'message': 'Login successful','name':user}), 200
        else:
            return jsonify({'success': False, 'message': 'Invalid User ID or Password'}), 401
    else:
        return jsonify({'success': False, 'message': 'User ID and Password are required'}), 400


@app.route('/events')
def events():
    conn = None
    cursor = None
    try:
        conn = mysql.connection
        cur=conn.cursor()
        cur.execute("SELECT * FROM events")
        events = cur.fetchall()
        
        response = jsonify(events)
        response.status_code = 200
        return response
    except Exception as e:
        print(e)
        return jsonify({'error': str(e)}), 500
    

    
@app.route('/contactUs',methods=['POST'])
def contactUs():
    try:
        _json = request.json
        _userid = _json['user_id']    
        _email=_json['email']   
        _message=_json['message']

        if _userid and _email and _message and request.method=='POST':
            conn=mysql.connection
            cur=conn.cursor()
            sqlQuery="INSERT INTO contactus Values(%s,%s,%s,default)"
            bindData=(_userid,_email,_message)
            cur.execute(sqlQuery,bindData)
            conn.commit()
            response=jsonify(result='Message sent Successfully')
            response.status_code=200
            return response
        else:
            return showMessage()
    except Exception as e:
        print(e)


@app.route('/electives')
def getelectives():
    try:
        conn = mysql.connection
        cur=conn.cursor()
        cur.execute("SELECT * FROM electives ORDER BY hours ASC ")
        result = cur.fetchall()
        electiveslist = []
        for row in result:
            elective = {'id': row[0], 'name': row[1], 'hours': row[2]}
            electiveslist.append(elective)
        return jsonify(electiveslist)

    except Exception as e:
        return jsonify({'error': str(e)})

@app.route('/courses')
def courses():
    conn = None
    cursor = None
    try:
        conn = mysql.connection
        cur=conn.cursor()
        cur.execute("SELECT * FROM courses")
        events = cur.fetchall()
        
        response = jsonify(events)
        response.status_code = 200
        return response
    except Exception as e:
        print(e)
        return jsonify({'error': str(e)}), 500 


@app.errorhandler(404)
def showMessage(error=None):
    message = {
        'status': 404,
        'message': 'Record not found: ' + request.url, 
    }
    respone = jsonify(message)
    respone.status_code = 404
    return respone


if __name__ == "__main__":
    #app.run(host='192.168.1.4')
    app.run(debug=True)
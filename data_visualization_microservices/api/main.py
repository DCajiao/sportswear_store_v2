# API con Flask para 

# 1. actualizar las gráficas en tiempo real.
# 2. cargarlas en la página web.
# 3. Retornarlas a POSTMAN.

from flask import Flask, jsonify, send_file

#----------------------------------------------------------------
app = Flask(__name__)
#----------------------------------------------------------------

@app.route('/KEEPALIVE')
def mainpage():
    msg= "<center><h1>If you can see this message, it means that the API is still alive.</h1></center>"
    return msg

# ----------------------------------------------------------------

if __name__ == '__main__': 
    app.run(debug=True, host='0.0.0.0')
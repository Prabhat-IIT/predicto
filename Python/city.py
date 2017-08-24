import scrap
import requests


import pandas as pd
from flask import Flask
from flask import jsonify
from flask import request


#page = requests.get("http://164.100.222.56/amb/1/mandishowtoday.asp")
page=requests.get("http://0.0.0.0:8000/scrap.html")
app=Flask(__name__)
city=pd.read_csv('city.csv')
city=city.iloc[:,1:]
json=city.iloc[:,:].to_json(orient='records')
sa={"city":"Damoh"}
  
@app.route('/city',methods=['POST'])
def getCityDetail():
   data=request.get_json(force=True)
   a=data.get("city")
   city_info=city.loc[city['name']== a].to_json(orient='records')
   return city_info
   
@app.route('/seed_price',methods=['Get'])
def getseedprice():
  
   return scrap.seed_price(page)
    

 
if __name__ == '__main__':
 app.run(host='192.168.43.96')



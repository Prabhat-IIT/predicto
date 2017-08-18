from bs4 import BeautifulSoup
import re
import operator
import pandas as pd
import numpy as np
from flask import jsonify
import json
from tabulate import tabulate
import sys
from stop_words import get_stop_words
import requests


def seed_price(page):
 #page=requests.get("http://0.0.0.0:8000/scrap.html")
 soup = BeautifulSoup(page.content, 'html.parser')
 body=list(soup.children)[1]
 cfoutput=list(body.children)[3]
 hr=list(cfoutput.children)[5]
 cfout=list(hr.children)[3]
 hrout=list(cfout.children)[1]
 a=list(hrout.children)[1]
 b=a.findAll('span')
 x=0
 data=[]
 mandi,item,price,quantity="","","",""   
 for pt in b:
  if(x>5):
      if(x%4==1 ):
         mandi=re.sub('[^A-z|a-z]+','',pt.get_text()) 
      elif(x%4==2): 
         item=pt.get_text()
      elif(x%4==3):
         price=pt.get_text() 
      elif(x%4==0): 
         quantity=pt.get_text()
      data.append([mandi,item,price,quantity])
     
  x=x+1
  
 array=np.array(data)
 df=pd.DataFrame(array,columns=['Mandi','Item','Price','Quantity'])
 df=df.replace('',np.nan)
 df=df.dropna(axis=0,how='any')
 return df.to_json(orient='records')


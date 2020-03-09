import psycopg2
from sqlalchemy import create_engine
import pandas as pd
import io

data = pd.read_pickle("food_data.pkl")
#print(data.head())

#Dev
engine = create_engine("postgres://fiegbkuvdmapic:78ed246c6344c6feca9e0543045043b4bb8a2efac2682cc11b7930c23165d237@ec2-3-224-165-85.compute-1.amazonaws.com:5432/d76g5sbjvh9r1j")

#Prod
#engine = create_engine("postgres://xqdudtnrtckoxw:c0aca721842c41d51784ee47ada74189f8367062e558328c89fe4f91c5b122e3@ec2-3-224-165-85.compute-1.amazonaws.com:5432/dckp2r7b31c5jb")

data.head(0).to_sql('food', engine, if_exists='replace',index=False)

conn = engine.raw_connection()
cur = conn.cursor()
output = io.StringIO()
data.to_csv(output, sep='\t', header=False, index=False)
output.seek(0)
contents = output.getvalue()
cur.copy_from(output, 'food', null="") # null values become ''
conn.commit()
import pymongo
import pandas as pd

#------------------------------------------------------#
# Functions to connect to MongoDB Atlas and query data #
#------------------------------------------------------#

def connect_to_mongodb_atlas():
    """
    Connect to MongoDB Atlas and return the database object.

    Returns:
        db (Database): The MongoDB database object for 'sportswear_store'.
    """
    client = pymongo.MongoClient("mongodb+srv://sportswear_store:admin123@cluster1.wh1pvt9.mongodb.net/")
    db = client["sportswear_store"] # Select the database
    return db

def aggregate_query_to_atlas(db, collection_name, query):
    """
    Perform an aggregation query on a MongoDB collection and return the results as a pandas DataFrame.

    Args:
        db (Database): The MongoDB database object.
        collection_name (str): The name of the collection to query.
        query (list): The aggregation pipeline query.

    Returns:
        DataFrame: A pandas DataFrame containing the query results.
    """
    collection = db[collection_name] # Access the collection
    try:
        cursor = collection.aggregate(query)
        data = list(cursor) # Get the documents based on the query
        df = pd.DataFrame(data) # Convert the data to a pandas DataFrame
        return df
    except Exception as e:
        print(f"An error occurred: {e}")
        return pd.DataFrame()

def find_query_to_atlas(db, collection_name, query, projection=None, sort=None, limit=None):
    """
    Perform a find query on a MongoDB collection and return the results as a pandas DataFrame.

    Args:
        db (Database): The MongoDB database object.
        collection_name (str): The name of the collection to query.
        query (dict): The query criteria.
        projection (dict, optional): The fields to include or exclude in the result set.
        sort (list of tuple, optional): The sort order for the results.
        limit (int, optional): The maximum number of documents to return.

    Returns:
        DataFrame: A pandas DataFrame containing the query results.
    """
    collection = db[collection_name]  # Access the collection
    try:
        cursor = collection.find(query, projection)
        
        if sort:
            cursor = cursor.sort(sort)
        
        if limit:
            cursor = cursor.limit(limit)
        
        data = list(cursor)  # Get the documents based on the query
        df = pd.DataFrame(data)  # Convert the data to a DataFrame of pandas
        return df
    except Exception as e:
        print(f"An error occurred: {e}")
        return pd.DataFrame()

#-------------------------------------------------------

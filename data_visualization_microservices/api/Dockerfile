FROM python:3.11

WORKDIR /api

COPY requirements.txt .

RUN pip install --no-cache-dir -r requirements.txt

COPY main.py .
COPY mongoAtlas_client.py .
COPY plotter.py .

RUN chmod +x main.py

EXPOSE 5000

CMD ["python", "main.py"]
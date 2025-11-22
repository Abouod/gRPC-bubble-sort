FROM python:3.14-slim

WORKDIR /app
COPY bubble_sort/ ./bubble_sort/
WORKDIR /app/bubble_sort

RUN python -m pip install --no-cache-dir grpcio protobuf

# default entrypoint so you can run any script, e.g.
# docker-compose run --rm client python bubble_client.py
ENTRYPOINT ["python3"]

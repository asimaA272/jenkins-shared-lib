def call(String alias, int port) { sh ". venv/bin/activate && python3 src/deploy.py --alias ${alias} --port ${port}" }

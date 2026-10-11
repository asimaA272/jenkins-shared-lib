def call(String alias) { sh ". venv/bin/activate && python3 src/test_model.py --alias ${alias}" }

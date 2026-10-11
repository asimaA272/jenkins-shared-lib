def call(String sourceAlias, String newAlias) { sh ". venv/bin/activate && python3 src/promote.py alias --source-alias ${sourceAlias} --new-alias ${newAlias}" }

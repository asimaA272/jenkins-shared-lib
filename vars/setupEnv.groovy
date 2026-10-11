def call() {
    sh '''
        PY=$(command -v python3.12 || true)
        if [ -z "$PY" ]; then echo "ERROR: python3.12 nahi mila"; exit 1; fi
        if [ -d venv ] && ! venv/bin/python --version 2>&1 | grep -q "3.12"; then
            echo "Purana venv hata raha hoon"; rm -rf venv
        fi
        [ -d venv ] || $PY -m venv venv
        . venv/bin/activate
        pip install -q --upgrade pip
        pip install -q -r requirements.txt
        python --version
    '''
}

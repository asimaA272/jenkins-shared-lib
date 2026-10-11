def call() {
    sh """
        if [ ! -d "venv" ]; then
            python3 -m venv venv
        fi
        . venv/bin/activate
        pip install --upgrade pip
        pip install -r requirements.txt
    """
}

"""Gmail se seedha mail bhejta hai (Jenkins email plugin ke baghair)."""
import os
import smtplib
import sys
from email.message import EmailMessage

user = os.environ["SMTP_USER"].strip()
pw = os.environ["SMTP_PASS"].replace(" ", "")
to = [a.strip() for a in os.environ["EMAIL_TO"].split(",") if a.strip()]
subject = open(".mail_subject.txt", encoding="utf-8").read().strip()
body = open(".mail_body.txt", encoding="utf-8").read()

msg = EmailMessage()
msg["From"] = user
msg["To"] = ", ".join(to)
msg["Subject"] = subject
msg.set_content(body)

try:
    with smtplib.SMTP_SSL("smtp.gmail.com", 465, timeout=30) as s:
        s.login(user, pw)
        s.send_message(msg)
    print("EMAIL SENT to", ", ".join(to))
except Exception as e:
    print("EMAIL FAILED:", repr(e))
    sys.exit(1)

from flask import Flask, request, jsonify, redirect, url_for, render_template, session
import os
import cv2
import numpy as np
import json
import requests

app = Flask(__name__)
app.secret_key = b'_5#y2L"F4Q8z\n\xec]/'
UPLOAD_FOLDER = './serverimage/'
SPRINGBOOT_API_URL = 'http://localhost:8080/updatePersonalColor'

def pcolor_analysis(image):
    try:
        lab_image = cv2.cvtColor(image, cv2.COLOR_BGR2LAB)
        avg_lab_b = np.mean(lab_image[:, :, 2])
        return avg_lab_b
    except Exception as e:
        print(f"이미지 처리 오류: {e}")
        raise e

def predict_personal_color(image_path, dataset):
    try:
        image = cv2.imread(image_path)
        avg_b_value = pcolor_analysis(image)
        closest_match = min(dataset.items(), key=lambda x: abs(x[1] - avg_b_value))
        personal_color = closest_match[0]
        return personal_color, avg_b_value
    except Exception as e:
        print(f"퍼스널 컬러 예측 오류: {str(e)}")
        return "Unknown", np.nan

def load_dataset():
    try:
        with open("your_dataset.json", "r") as file:
            loaded_dataset = json.load(file)
        return loaded_dataset
    except (FileNotFoundError, json.JSONDecodeError):
        return {}

your_generated_dataset = load_dataset()
personal_color_names = {
    "봄 웜톤": "AW",
    "여름 쿨톤": "SC",
    "가을 웜톤": "AW",
    "겨울 쿨톤": "WC"
}

@app.route('/analyze', methods=['POST'])
def analyze():
    print("받은 요청: /analyze")
    uploaded_file = request.files['file']
    if uploaded_file.filename != '':
        if not os.path.exists(UPLOAD_FOLDER):
            os.makedirs(UPLOAD_FOLDER)
        file_path = os.path.join(UPLOAD_FOLDER, uploaded_file.filename)
        uploaded_file.save(file_path)
        try:
            predicted_personal_color, avg_b_value = predict_personal_color(file_path, your_generated_dataset)
            os.remove(file_path)
            personal_color_name = personal_color_names.get(predicted_personal_color, "Unknown")
            print("예측된 퍼스널 컬러:", personal_color_name)
            print("평균 B 값:", avg_b_value)

            user_id = session.get('user_id')
            if user_id:
                try:
                    response = requests.post(SPRINGBOOT_API_URL, json={"userId": user_id, "colorId": personal_color_name})
                    if response.status_code == 200:
                        print("퍼스널 컬러 정보를 스프링부트의 데이터베이스에 저장했습니다.")
                    else:
                        print("퍼스널 컬러 정보 저장에 실패했습니다.")
                except Exception as e:
                    print("API 요청 중 오류 발생:", e)
            else:
                print("사용자 정보를 찾을 수 없습니다.")

            return jsonify({"personal_color": personal_color_name, "avg_b_value": avg_b_value}), 200
                                                    
        except Exception as e:
            os.remove(file_path)
            print(f"이미지 {file_path} 퍼스널 컬러 예측 오류: {e}")
            return jsonify({"error": str(e)}), 500
    else:
        return jsonify({"error": "이미지를 업로드해주세요."}), 400

@app.route('/login', methods=['POST'])
def login():
    user_id = request.form.get('user_id')
    # 로그인 로직 추가
    session['user_id'] = user_id
    return redirect(url_for('index'))

@app.route('/WCview')
def show_WC_view():
    return render_template('WCview.html')

@app.route('/SCview')
def show_SC_view():
    return render_template('SCview.html')

@app.route('/AWview')
def show_AW_view():
    return render_template('AWview.html')

@app.route('/SWview')
def show_SW_view():
    return render_template('SWview.html')

if __name__ == '__main__':
    app.run(debug=True, port=5555)

import openai

# OpenAI API 키 설정 (자신의 API 키를 입력)
openai.api_key = "YOUR_API_KEY"

def generate_code(prompt):
    response = openai.ChatCompletion.create(
        model="gpt-4",  # 최신 모델 사용 가능
        messages=[{"role": "system", "content": "You are a helpful coding assistant."},
                  {"role": "user", "content": prompt}]
    )
    return response["choices"][0]["message"]["content"]

# 예제 사용
user_prompt = "파이썬으로 퀵 정렬 알고리즘을 구현해줘"
generated_code = generate_code(user_prompt)
print(generated_code)
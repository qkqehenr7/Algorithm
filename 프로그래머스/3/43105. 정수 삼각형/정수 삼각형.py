def solution(triangle):

    t = len(triangle)
    dp = [[0] * (i + 1) for i in range(t)]
    dp[0][0] = triangle[0][0]
    
    # 첫 번째는 제외해도 됨
    for i in range(1, t) :
        # 이번 배열의 길이만큼 반복
        for j in range(len(triangle[i])) :
            # 첫 번째 원소인 경우
            if j == 0:
                dp[i][j] = dp[i-1][j] + triangle[i][j]
            # 마지막 원소인 경우
            elif j == len(triangle[i]) - 1:
                dp[i][j] = dp[i-1][-1] + triangle[i][j]
            else:
                # 중간 원소 (선택지가 2개)
                dp[i][j] = max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j]
    
    # dp 마지막 배열에서 최댓값 선택
    return max(dp[t-1])
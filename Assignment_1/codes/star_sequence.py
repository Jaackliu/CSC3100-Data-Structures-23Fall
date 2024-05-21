
def matrix_exp(matrix, power, mod):
    order = bin(power)
    ans_matrix = [1, 0, 0, 1]
    while order[-1] != 'b':
        if order[-1] == '1':
            ans_matrix = matrix_multiply(ans_matrix, matrix, mod)
        matrix = matrix_multiply(matrix, matrix, mod)
        order = order[:-1]
    return ans_matrix


def matrix_multiply(mx_1, mx_2, mod):
    if len(mx_2) == 4:
        return [(mx_1[0] * mx_2[0] + mx_1[1] * mx_2[2]) % mod,
                (mx_1[0] * mx_2[1] + mx_1[1] * mx_2[3]) % mod,
                (mx_1[2] * mx_2[0] + mx_1[3] * mx_2[2]) % mod,
                (mx_1[2] * mx_2[1] + mx_1[3] * mx_2[3]) % mod]
    else:
        return [mx_1[0] * mx_2[0] + mx_1[1] * mx_2[1],
                mx_1[2] * mx_2[0] + mx_1[3] * mx_2[1]]


def main():
    input_list = input().split()
    n = int(input_list[0])
    a = int(input_list[1])
    b = int(input_list[2])
    f_0 = int(input_list[3])
    f_1 = int(input_list[4])
    m = int(input_list[5])
    matrix_after_exp = matrix_exp([a, b, 1, 0], n - 1, m)
    ans = (matrix_after_exp[0] * f_1 + matrix_after_exp[1] * f_0) % m
    print(ans)


main()

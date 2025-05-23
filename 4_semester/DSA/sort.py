import io
import sys


def validate_header(header):
    error_messages = {
        0: "Error: Maximum neni kladne!",
        1: "Error: Neznamy typ razeni posloupnosti!",
        2: "Error: Nelze urcit, zda posloupnost napadl virus!"
    }
    valid = True
    message = None

    if header[0] <= 0:
        valid = False
        message = error_messages.get(0)
    elif header[1] not in [0, 1, 2]:
        valid = False
        message = error_messages.get(1)
    elif header[2] not in [0, 1]:
        valid = False
        message = error_messages.get(2)

    return {"status": valid, "message": message}


def validate_number(number, maximum):
    return 0 < number <= maximum


def validate_sort(number, index, sequence, sort_type):
    if index == 0:
        return True

    if sort_type == 1 and number < sequence[index - 1]:
        return False

    if sort_type == 2 and number > sequence[index - 1]:
        return False

    return True


def validate_sequence_length(sequence):
    error_messages = {
        0: "Error: Posloupnost ma mene nez 1000 prvku!",
        1: "Error: Posloupnost ma vic nez 1000000 prvku!"
    }
    valid = True
    message = None

    if len(sequence) < 1000:
        valid = False
        message = error_messages.get(0)
    elif len(sequence) > 1000000:
        valid = False
        message = error_messages.get(1)

    return {"status": valid, "message": message}


def counting_sort(sequence, maximum):
    counter = [0] * (maximum + 1)

    for num in sequence:
        counter[num] += 1

    result = [0] * len(sequence)

    index = 0
    for key, value in enumerate(counter):
        for _ in range(value):
            result[index] = key
            index += 1

    return result


def merge_sort(sequence):
    if len(sequence) <= 1:
        return sequence

    if len(sequence) <= 10:
        return insertion_sort(sequence)

    mid = len(sequence) // 2
    left_half = sequence[:mid]
    right_half = sequence[mid:]

    left_half = merge_sort(left_half)
    right_half = merge_sort(right_half)

    i = j = 0
    result = []
    while i < len(left_half) and j < len(right_half):
        if left_half[i] < right_half[j]:
            result.append(left_half[i])
            i += 1
        else:
            result.append(right_half[j])
            j += 1

    result += left_half[i:]
    result += right_half[j:]

    return result


def insertion_sort(sequence):
    for i in range(1, len(sequence)):
        if sequence[i] < sequence[i - 1]:
            key = sequence[i]
            j = i - 1
            while j >= 0 and sequence[j] > key:
                sequence[j + 1] = sequence[j]
                j -= 1

            sequence[j + 1] = key

    return sequence


def choose_sorting_algorithm(sequence, maximum):
    if maximum <= 10000:
        return counting_sort(sequence, maximum)

    return merge_sort(sequence)


def parse_input(input_str):
    header, *sequence = map(str.strip, input_str.split("\n"))
    header = list(map(int, header.split()))
    sequence = list(map(int, filter(None, sequence)))
    return header, sequence


def write_output(result):
    output_buffer = io.BytesIO()
    buffered_writer = io.BufferedWriter(output_buffer)

    block_size = 4096
    for i in range(0, len(result), block_size):
        block = "\n".join(map(str, result[i:i + block_size])).encode()
        buffered_writer.write(block)
        buffered_writer.write(b"\n")
        buffered_writer.flush()

    sys.stdout.buffer.write(output_buffer.getvalue())
    sys.stdout.flush()


def main():
    input_buffer = io.BytesIO()
    while True:
        chunk = sys.stdin.buffer.read(4096)
        if not chunk:
            break
        input_buffer.write(chunk)

    input_str = input_buffer.getvalue().decode()
    header, sequence = parse_input(input_str)

    header_validation_result = validate_header(header)
    if not header_validation_result["status"]:
        sys.stderr.write(header_validation_result["message"] + "\n")
        sys.exit(1)

    for index, number in enumerate(sequence):
        if not validate_number(number, header[0]):
            sys.stderr.write("Error: Prvek posloupnosti je mimo rozsah!\n")
            sys.exit(1)

        if header[2] != 1 and header[1] != 0:
            if not validate_sort(number, index, sequence, header[1]):
                sys.stderr.write("Error: Posloupnost neni usporadana!\n")
                sys.exit(1)

    sequence_validation_result = validate_sequence_length(sequence)
    if not sequence_validation_result["status"]:
        sys.stderr.write(sequence_validation_result["message"] + "\n")
        sys.exit(1)

    result = []

    if header[1] == 0:
        result = choose_sorting_algorithm(sequence, header[0])
    else:
        if header[1] == 2:
            sequence.reverse()

        if header[2] == 1:
            result = insertion_sort(sequence)
        else:
            result = sequence

    write_output(result)


if __name__ == "__main__":
    main()

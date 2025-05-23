from collections import deque, namedtuple
import sys

# Определение именованного кортежа для хранения информации о точке
Point = namedtuple('Point', ['prev', 'slope', 'length'])


def build_path(solution, point):
    path = []
    while point is not None:
        path.append(point)
        point = solution[point[0]][point[1]].prev
    return path


def find_paths_bfs(map, reindex, cmp):
    rows, cols = len(map), len(map[0])
    solution = [[Point((0, 0), -1, -1) for _ in range(cols)] for _ in range(rows)]
    x0, y0 = reindex(0, 0)
    solution[x0][y0] = Point(None, 0, 0)
    queue = deque([(x0, y0)])

    directions = [(0, 1), (1, 0), (-1, 0), (0, -1)]

    while queue:
        x, y = queue.popleft()
        current_point = solution[x][y]

        for dx, dy in directions:
            x1, y1 = x + dx, y + dy
            if 0 <= x1 < rows and 0 <= y1 < cols and map[x1][y1] > map[x][y]:
                new_slope = max(current_point.slope, map[x1][y1] - map[x][y])
                new_length = current_point.length + 1
                next_point = solution[x1][y1]

                if next_point.slope == -1 or cmp(new_length, next_point.length) or \
                        (next_point.length == new_length and cmp(next_point.slope, new_slope)):
                    solution[x1][y1] = Point((x, y), new_slope, new_length)
                    queue.append((x1, y1))

    return solution


def find_two_way_path(map, cmp):
    def update_max_point(i, j, up, down):
        nonlocal max_height, max_slope, max_point, min_length
        slope = max(up.slope, down.slope)
        length = up.length + down.length
        if (max_slope is None or map[i][j] > max_height or
                (map[i][j] == max_height and (cmp(max_slope, slope) or length < min_length))):
            max_slope = slope
            max_height = map[i][j]
            max_point = (i, j)
            min_length = length

    upsolution = find_paths_bfs(map, lambda x, y: (x, y), cmp)
    downsolution = find_paths_bfs(map, lambda x, y: (len(map) - 1 - x, len(map[0]) - 1 - y), cmp)

    max_height = max_slope = max_point = None
    min_length = float('inf')
    for i in range(len(map)):
        for j in range(len(map[0])):
            if upsolution[i][j].slope != -1 and downsolution[i][j].slope != -1:
                update_max_point(i, j, upsolution[i][j], downsolution[i][j])

    if max_point is None:
        print("Error: Cesta neexistuje!", file=sys.stderr)
        sys.exit(1)

    path_up = build_path(upsolution, max_point)
    path_down = build_path(downsolution, max_point)

    full_path = path_up[1:][::-1] + path_down
    if len(full_path) == 0:
        print("Error: Cesta neexistuje!", file=sys.stderr)
        sys.exit(1)

    return full_path


def read_matrix():
    try:
        size = input().split()
        rows = int(size[0])
        cols = int(size[1])
    except (IndexError, ValueError):
        print("Error: Chybny vstup!", file=sys.stderr)
        sys.exit(1)

    matrix = []
    for _ in range(rows):
        try:
            row = list(map(int, input().split()))
            if len(row) != cols:
                print("Error: Chybny vstup!", file=sys.stderr)
                sys.exit(1)
            matrix.append(row)
        except ValueError:
            print("Error: Chybny vstup!", file=sys.stderr)
            sys.exit(1)

    if len(matrix) != rows:
        print("Error: Chybny vstup!", file=sys.stderr)
        sys.exit(1)

    return matrix


def print_path(path, matrix):
    print(len(path))
    for x, y in path:
        print(matrix[x][y], end=" ")
    print()


def main():
    matrix = read_matrix()

    if len(sys.argv) != 2:
        print_path(find_two_way_path(matrix, lambda x, y: x < y), matrix)
        print_path(find_two_way_path(matrix, lambda x, y: x > y), matrix)
    else:
        argument = sys.argv[1]
        if argument == "lift":
            print_path(find_two_way_path(matrix, lambda x, y: x < y), matrix)
        elif argument == "piste":
            print_path(find_two_way_path(matrix, lambda x, y: x > y), matrix)
        else:
            print(f"Chyba: Špatná hodnota argumentu '{argument}'.", file=sys.stderr)
            sys.exit(1)


if __name__ == "__main__":
    main()

#include "telescope-1.hpp"
#include <sstream>
#include <string>
#include <cctype>
#include <vector>
#include <stdexcept>
#include <algorithm>

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wsign-compare"
using namespace std;

pair<size_t, size_t> parse_matrix(istream& in) {
    size_t rows = 0;
    size_t cols = 0;
    string line;
    while (getline(in, line)) {
        rows++;
        size_t column_count = 0;
        bool prev = false;
        for(char c : line) {
            if(prev && !isdigit(c)) {
                prev = false;
            } else if(!prev && isdigit(c)) {
                prev = true;
                column_count++;
            }
        }
        if(cols == 0) {
            cols = column_count;
        } else if (cols != column_count) {
            throw invalid_argument("Inconsistent columns count.");
        }
    }
    return {rows, cols};
}
vector<int> parse_matrix(istream& in, const pair<size_t, size_t>& m_size) {
    string line;
    vector<int> result;
    size_t rowCount = 0;
    while (getline(in, line)) {
        if (line.empty()) break;
        rowCount++;
        size_t columnsCount = 0;
        istringstream iss(line);
        int num;
        while (iss >> num) {
            result.push_back(num);
            columnsCount++;
        }
        if (columnsCount != m_size.second) {
            throw invalid_argument("Inconsistent columns count.");
        }
    }

    if (rowCount != m_size.first) {
        throw invalid_argument("Inconsistent columns count.");
    }

    return result;
}
void print_matrix(std::ostream& out, const std::pair<size_t, size_t>& m_size, const std::vector<int>& vec) {
    if (vec.empty()) {
        cout << "" << std::endl;
        return;
    }
    int columnCount = int(m_size.second);
    int maxSize = 0;
    for_each(vec.begin(), vec.end(), [&](int i) {
        int length = int(std::to_string(i).length());
        if (length > maxSize) {
            maxSize = length;
        }
    });
    int sepLineSize = ((maxSize + 2) * columnCount) + columnCount + 1;
    auto print_separator_line = [&]() {
        for (int i = 0; i < sepLineSize; ++i) {
            out << '-';
        }
        out << std::endl;
    };
    auto print_element = [&](int i) {
        char buffer[100];
        sprintf(buffer, " %*i ", maxSize, i);
        out << '|';
        out << buffer;
    };
    int index = 0;
    print_separator_line();
    for (int i : vec) {
        print_element(i);
        if ((index + 1) % columnCount == 0) {
            out << "|" << std::endl;
        }
        index++;
        if (index % columnCount != 0) {
            continue;
        }
    }

    print_separator_line();
}

vector<unsigned char> parse_stream(istream& in, const pair<size_t, size_t>& m_size) {
    vector<unsigned char> result(m_size.first * m_size.second);
    char* data = reinterpret_cast<char*>(result.data());
    in.read(data, m_size.first * m_size.second);
    if (in.gcount() != m_size.first * m_size.second) {
        throw invalid_argument("Inconsistent count.");
    }
    return result;
}

void rotate_down(const pair<size_t, size_t>& m_size, vector<unsigned char>& vec) {
    if (m_size.first == 1) {
        return;
    }
    size_t columns = m_size.second;
    size_t totalSize = vec.size();
    std::vector<unsigned char> result(totalSize);
    for (size_t i = 0; i < totalSize; ++i) {
        size_t newIndex = (i + columns) % totalSize;
        result[newIndex] = vec[i];
    }
    std::swap(vec, result);
}

void rotate_down(const pair<size_t, size_t>& m_size, vector<unsigned char>& vec, int step) {
    if (m_size.first == 1 || step == 0) {
        return;
    }
    size_t rows = m_size.first;
    size_t columns = m_size.second;
    if (step < 0) {
        step = int(rows) - ((-step) % int(rows));
    } else {
        step = int(step) % int(rows);
    }
    if (step == 0) {
        return;
    }
    std::vector<unsigned char> result(vec.size());

    for (size_t row = 0; row < rows; ++row) {
        for (size_t col = 0; col < columns; ++col) {
            int new_row = (int(row) + step) % int(rows);
            result[new_row * columns + col] = vec[row * columns + col];
        }
    }
    std::swap(vec, result);
}
void rotate_right(const pair<size_t, size_t>& m_size, vector<unsigned char>& vec) {
    if (m_size.second == 1) {
        return;
    }
    size_t rows = m_size.first;
    size_t columns = m_size.second;
    size_t size = vec.size();

    std::vector<unsigned char> result(size);

    for (size_t row = 0; row < rows; ++row) {
        for (size_t col = 0; col < columns; ++col) {
            size_t current_index = row * columns + col;
            size_t new_col = (col + 1) % columns;
            size_t new_index = row * columns + new_col;
            result[new_index] = vec[current_index];
        }
    }

    std::swap(vec, result);
}

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wsign-compare"
void rotate_right(const pair<size_t, size_t>& m_size, vector<unsigned char>& vec, int step) {
    if (m_size.second == 1 || step == 0) {
        return;
    }
    int columns = static_cast<int>(m_size.second);

    if (step < 0) {
        step = columns - ((-step) % columns);
    } else {
        step %= columns;
    }
    if (step == 0) {
        return;
    }
    std::vector<unsigned char> result(vec.size());
    for (int row = 0; row < m_size.first; ++row) {
        for (int col = 0; col < m_size.second; ++col) {
            int new_col = (col + step) % columns;
            int new_index = row * columns + new_col;
            int current_index = row * columns + col;
            result[new_index] = vec[current_index];
        }
    }
    std::swap(vec, result);
}
#pragma clang diagnostic pop


void swap_points(const std::pair<size_t, size_t>& m_size, std::vector<unsigned char>& vec, const Point& p1, const Point& p2) {
    size_t max_x = m_size.second;
    size_t max_y = m_size.first;
    if (p1.x < 0 || p1.x >= max_x || p1.y < 0 || p1.y >= max_y ||
        p2.x < 0 || p2.x >= max_x || p2.y < 0 || p2.y >= max_y) {
        throw std::invalid_argument("Out of matrix.");
    }
    size_t firstElemIndex = p1.y * max_x + p1.x;
    size_t secondElemIndex = p2.y * max_x + p2.x;
    std::swap(vec[firstElemIndex], vec[secondElemIndex]);
}

void swap_points(const std::pair<size_t, size_t>& m_size, std::vector<unsigned char>& vec, const Point& p1, const Point& p2, const Point& delta) {
    size_t max_x = m_size.second;
    size_t max_y = m_size.first;
    Point left_top1(p1.x, p1.y);
    Point right_bottom1(p1.x + delta.x - 1, p1.y + delta.y - 1);
    Point left_top2(p2.x, p2.y);
    Point right_bottom2(p2.x + delta.x - 1, p2.y + delta.y - 1);

    if (left_top1.x < 0 || left_top1.x >= max_x || left_top1.y < 0 || left_top1.y >= max_y ||
        right_bottom1.x < 0 || right_bottom1.x >= max_x || right_bottom1.y < 0 || right_bottom1.y >= max_y ||
        left_top2.x < 0 || left_top2.x >= max_x || left_top2.y < 0 || left_top2.y >= max_y ||
        right_bottom2.x < 0 || right_bottom2.x >= max_x || right_bottom2.y < 0 || right_bottom2.y >= max_y) {
        throw std::invalid_argument("Out of matrix.");
    }
    if (!(left_top1.x > right_bottom2.x || left_top2.x > right_bottom1.x || left_top1.y > right_bottom2.y || left_top2.y > right_bottom1.y)) {
        throw std::invalid_argument("Intersection of two matrices.");
    }
    for (size_t row = 0; row < delta.y; ++row) {
        for (size_t col = 0; col < delta.x; ++col) {
            size_t x1 = p1.x + col;
            size_t y1 = p1.y + row;
            size_t x2 = p2.x + col;
            size_t y2 = p2.y + row;
            size_t index1 = y1 * max_x + x1;
            size_t index2 = y2 * max_x + x2;
            std::swap(vec[index1], vec[index2]);
        }
    }
}
void decode_picture(const std::string& file, const std::pair<size_t, size_t>& m_size, std::vector<unsigned char>& vec) {
    std::ifstream in(file);

    if (in.is_open()) {
        std::string line;
        while (std::getline(in, line)) {
            char currentCommand = '_';
            std::vector<std::string> args = {};

            for (const auto& ch : line) {
                if (std::isalpha(ch)) {
                    currentCommand = ch;
                } else if (std::isdigit(ch) || ch == '-') {
                    args.back() += ch;
                } else {
                    args.emplace_back("");
                }
            }

            if (currentCommand == 'l') {
                int step = args.empty() ? -1 : std::stoi(args[0]);
                rotate_right(m_size, vec, -step);
            } else if (currentCommand == 'r') {
                int step = args.empty() ? 1 : std::stoi(args[0]);
                rotate_right(m_size, vec, step);
            } else if (currentCommand == 'd') {
                int step = args.empty() ? 1 : std::stoi(args[0]);
                rotate_down(m_size, vec, step);
            } else if (currentCommand == 'u') {
                int step = args.empty() ? -1 : std::stoi(args[0]);
                rotate_down(m_size, vec, -step);
            } else if (currentCommand == 's') {
                if (args.size() == 4) {
                    swap_points(m_size, vec, Point(std::stoull(args[0]), std::stoull(args[1])),
                                Point(std::stoull(args[2]), std::stoull(args[3])));
                } else if (args.size() == 6) {
                    swap_points(m_size, vec, Point(std::stoull(args[0]), std::stoull(args[1])),
                                Point(std::stoull(args[2]), std::stoull(args[3])),
                                Point(std::stoull(args[4]), std::stoull(args[5])));
                }
            }
        }
        in.close();
    }
}

#pragma clang diagnostic pop
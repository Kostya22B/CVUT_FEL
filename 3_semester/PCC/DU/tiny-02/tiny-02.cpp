#include "tiny-02.hpp"

#include <algorithm>
#include <tuple>
#include <sstream>
#include <set>

std::pair<size_t, int*> parse_line(std::string const& line) {
    std::stringstream sstr(line);
    size_t line_size;
    sstr >> line_size;
    int* data = new int[line_size];
    for (size_t i = 0; i < line_size; ++i) {
        sstr >> data[i];
    }
    return {line_size, data};
}

bool validate_data(const int* data, size_t sz) {
    for (size_t i = 0; i < sz; ++i) {
        if (data[i] < 0 || data[i] > 255) {
            return false;
        }
    }
    return true;
}

std::vector<statistics> summarize_data(std::istream& in) {
    std::vector<statistics> summary;
    
    std::string line;
    while (std::getline(in, line)) {
        size_t num_data;
        int* data = nullptr; //null pointer
        std::tie(num_data, data) = parse_line(line);
        if (validate_data(data, num_data)) {
            std::sort(data, data+num_data);
            auto median = (num_data % 2 == 1)? data[num_data/2] : (data[(num_data - 1) /2] + data[num_data/2]) / 2;
            summary.push_back({num_data, data[0], data[num_data-1], median});
        }
        delete[] data;
        //deleting cashe(heap)
    }
    
    return summary;
}

#include "tiny-03.hpp"
#include <exception>
#include <tuple>
#include <sstream>
#include <algorithm>
#include <vector>
#include <limits>

class fixed_array {
    int* m_data = nullptr;
    size_t m_size = 0;

public:
    fixed_array(size_t size) : m_size(size) {
        m_data = new int[m_size];
    }

    // Define a destructor to release memory
    ~fixed_array() {
        delete[] m_data;
    }

    int& at(size_t idx) {
        return m_data[idx];
    }

    int const& at(size_t idx) const {
        return m_data[idx];
    }

    int* data() {
        return m_data;
    }

    size_t size() const {
        return m_size;
    }

    // Define a move constructor
    fixed_array(fixed_array&& rhs) : m_data(rhs.m_data), m_size(rhs.m_size) {
        rhs.m_data = nullptr;
        rhs.m_size = 0;
    }

    // Define a move assignment operator
    fixed_array& operator=(fixed_array&& rhs) {
        if (this != &rhs) {
            delete[] m_data; // Release existing memory
            m_data = rhs.m_data;
            m_size = rhs.m_size;
            rhs.m_data = nullptr;
            rhs.m_size = 0;
        }
        return *this;
    }
};

fixed_array parse_line(std::string const& line) {
    std::stringstream sstr(line);
    size_t line_size;
    sstr >> line_size;

    fixed_array fa(line_size);

    for (size_t i = 0; i < line_size; ++i) {
        sstr >> fa.at(i);
    }
    return fa;
}

bool validate_data(fixed_array const& data) {
    for (size_t i = 0; i < data.size(); ++i) {
        if (data.at(i) < 0 || data.at(i) > 255) {
            return false;
        }
    }
    return true;
}

std::vector<statistics> summarize_data(std::istream& in) {
    std::vector<statistics> summary;

    std::string line;
    while (std::getline(in, line)) {
        auto data = parse_line(line);
        if (validate_data(data)) {
            std::sort(data.data(), data.data() + data.size());
            auto median = (data.size() % 2 == 1) ? data.at(data.size() / 2)
                                                 : (data.at((data.size() - 1) / 2) + data.at(data.size() / 2)) / 2;
            summary.push_back({data.size(), data.at(0), data.at(data.size() - 1), median});
        } else {
            // For invalid data, set statistics to indicate the presence of invalid values
            summary.push_back({std::numeric_limits<size_t>::max(), -1, -1, std::numeric_limits<int>::max()});
        }
    }

    return summary;
}

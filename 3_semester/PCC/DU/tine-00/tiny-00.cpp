#include "tiny-00.hpp"
#include <iomanip>
#include <algorithm>
#include <numeric>

void write_stats(std::vector<double> const& data, std::ostream& out) {
    if (data.empty()) {
        out << "";
        return;
    }

    double min_val = *std::min_element(data.begin(), data.end());
    double max_val = *std::max_element(data.begin(), data.end());
    double sum = std::accumulate(data.begin(), data.end(), 0.0);
    double mean = sum / data.size();

    out << "min: " << std::fixed << std::setprecision(2) << min_val << std::endl;
    out << "max: " << std::fixed << std::setprecision(2) << max_val << std::endl;
    out << "mean: " << std::fixed << std::setprecision(2) << mean << std::endl;
}

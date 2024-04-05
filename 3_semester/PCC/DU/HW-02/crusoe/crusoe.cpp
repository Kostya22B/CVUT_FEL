#include "crusoe.hpp"

#include <utility>
#include <queue>
#include <stack>

using namespace std;

static const std::vector<std::string> colors{
    "red", "olive", "orange", "lightblue", "yellow",
    "pink", "cyan", "purple", "brown", "magenta"};
vertex::vertex(std::string str, int x, int y, std::string  col):
        xy(x, y),
        name(std::move(str)),
        c_forward(std::move(col))
{}

bool vertex::add_neighbour(size_t vertex_index, const std::string& color) {
    bool is_already_neighbour = std::any_of(neighbours.begin(), neighbours.end(),
    [vertex_index](const std::pair<size_t, std::string>& neighbour) {
    return neighbour.first == vertex_index;
    });

    if (is_already_neighbour) {
        return false;
    }

    neighbours.emplace_back(vertex_index, color);
    return true;
}

std::vector<std::pair<size_t, std::string>> vertex::get_neighbour() const {
    return this->neighbours;
}

std::pair<int, int> vertex::get_xy() const {
    return this->xy;
}

void vertex::set_color(const std::string& col) {
    this->c_forward = col;
}

std::string vertex::get_color() const {
    return this->c_forward;
}

void vertex::set_edge_color(size_t vv, const std::string& col){
    for (auto & blob : this->neighbours){
        if (blob.first == vv){
            blob.second = col;
            break;
        }
    }
}

std::string vertex::get_edge_color(size_t vv){
    for ( const auto & blob : this->neighbours){
        if (blob.first == vv){
            return blob.second;
        }
    }
    return "#FFFFFF";
}


void graph::add_vertex(int x, int y, const std::string& col){
    this->vertices.emplace_back(std::to_string(this->num_elem++), x, y, col);
}

void graph::add_edge(size_t v1, size_t v2, const string &col) {
    if (v1 >= num_elem || v2 >= num_elem) {
        return;
    }
    vertices[v1].add_neighbour(v2, col);
    vertices[v2].add_neighbour(v1, col);
}

bool graph::is_edge(size_t v1, size_t v2) const {
    if (v1 >= num_elem || v2 >= num_elem) {
        return false;
    }

    const vector<pair<size_t, string>> &neighbors = vertices[v1].get_neighbour();
    for (const auto &neighbour: neighbors)
        if (neighbour.first == v2) {
            return true;
        }


    return false;
}

string graph::edge_color(size_t v1, size_t v2) const {
    if (v1 >= num_elem || v2 >= num_elem) {
        return "#FFFFFF";
    }

    const vector<pair<size_t, string>> &neighbors = vertices[v1].get_neighbour();
    for (const auto &neighbour : neighbors) {
        if (neighbour.first == v2) {
            return neighbour.second;
        }
    }

    return "#FFFFFF";
}

std::string graph::vertex_color(size_t v1) const {
    if (v1 >= num_elem) {
        return "#FFFFFF";
    }

    return vertices[v1].get_color();
}

void graph::set_vertex_color(size_t v1, const std::string &col) {
    if (v1 < num_elem) {
        vertices[v1].set_color(col);
    }
}

void graph::set_edge_color(size_t v1, size_t v2, const std::string &col) {
    if (v1 < num_elem && v2 < num_elem) {
        vertices[v1].set_edge_color(v2, col);
        vertices[v2].set_edge_color(v1, col);
    }
}


bool graph::empty() const {
    return vertices.empty();
}

size_t graph::size() const {
    return num_elem;
}

size_t graph::num_edge() const {

    size_t edges = 0;

    for(const auto & vertice : vertices) {
        auto neighbors = vertice.get_neighbour();
        edges += neighbors.size();
    }

    return edges / 2;
}


vertex graph::get_vertex(size_t num) const {
    return vertices[num];
}

void graph::is_achievable(size_t from, vector<size_t> &achieved) {
    vector<bool> visited(num_elem, false);
    stack<size_t> to_visit;

    visited[from] = true;
    to_visit.push(from);

    while (!to_visit.empty()) {
        size_t current = to_visit.top();
        to_visit.pop();
        achieved.push_back(current);

        for (const auto &v : vertices[current].get_neighbour()) {
            if (!visited[v.first]) {
                visited[v.first] = true;
                to_visit.push(v.first);
            }
        }
    }
}

void graph::color_component(vector<size_t> cmp, const string &col) {
    for (const auto &i : cmp) {
        vertices[i].set_color(col);
        for (const auto &n : vertices[i].get_neighbour()) {
            vertices[n.first].set_edge_color(i, col);
        }
    }
}

std::vector<size_t> graph::path(size_t v1, size_t v2) {
    std::vector<bool> visited(num_elem, false);
    std::vector<long long int> prev(num_elem, -1);
    std::queue<size_t> queue;
    std::vector<size_t> path;

    if (num_elem == 0 || v1 >= num_elem || v2 >= num_elem) {
        return path;
    }

    queue.push(v1);
    visited[v1] = true;
    prev[v1] = -1;

    while (!queue.empty()) {
        size_t v = queue.front();
        queue.pop();

        for (const auto &vv : vertices[v].get_neighbour()) {
            if (!visited[vv.first]) {
                queue.push(vv.first);
                prev[vv.first] = v;
                visited[vv.first] = true;
            }
        }
    }

    size_t current = v2;
    if (prev[current] == -1) {
        return path;
    }

    while (current != static_cast<size_t>(-1)) {
        path.push_back(current);
        current = prev[current];
    }

    std::reverse(path.begin(), path.end());

    return path;
}

void graph::color_path(vector<size_t> pth, const string &col) {
    if (pth.empty() || num_elem == 0) {
        return;
    }
    for (size_t i = 1; i < pth.size(); i++) {
        set_edge_color(pth[i - 1], pth[i], col);
    }
}

graph::graph_comp::graph_comp(graph &gg) : gg(gg) {
    std::vector<bool> visited(gg.size(), false);

    for (size_t i = 0; i < visited.size(); i++) {
        if (visited[i]) {
            continue;
        }
        std::vector<size_t> ach;
        gg.is_achievable(i, ach);
        this->components.push_back(ach);
        for (const auto &blob : ach) {
            visited[blob] = true;
        }
    }
}

void graph::graph_comp::color_componennts() {
    size_t colorsQuantity = colors.size();
    size_t currentColorIndex = 0;

    for (const auto &component : components) {
        if (!component.empty()) {
            gg.color_component(component, colors[currentColorIndex % colorsQuantity]);
            currentColorIndex++;
        }
    }
}

size_t graph::graph_comp::count() const {
    return components.size();
}

size_t graph::graph_comp::count_without_one() const {
    size_t count = 0;

    for (const auto &c : components) {
        if (c.size() > 1) count++;
    }

    return count;
}

size_t graph::graph_comp::max_comp() const {

    if(components.empty()) return 0;
    size_t max_size = 0;
    size_t max_index = 0;
    for(size_t i = 0; i < components.size(); i++) {
        size_t size = components[i].size();
        if(size > max_size) {
            max_size = size;
            max_index = i;
        }
    }
    return max_index;
}

size_t graph::graph_comp::size_of_comp(size_t i) const {
    if (i < components.size()) {
        return components[i].size();
    } else {
        return 0;
    }
}

std::vector<size_t> graph::graph_comp::get_component(size_t i) const {
    return i < components.size() ? components[i] : std::vector<size_t>();
}

bool graph::graph_comp::same_comp(size_t v1, size_t v2) const {
    if (v1 < gg.size() && v2 < gg.size()) {
        vector<size_t> cmp;
        gg.is_achievable(v1, cmp);

        for (const auto &v : cmp) {
            if (v == v2) {
                return true;
            }
        }
    }

    return false;
}

graph::graph_fence::graph_fence(graph& gg, size_t vv, size_t distance) : gg(gg)
{
    std::vector<size_t> cost(gg.size(), gg.size());
    std::vector<size_t> parent(gg.size(), gg.size());
    cost[vv] = 0;
    parent[vv] = vv;

    std::queue<size_t> q;
    q.push(vv);
    fence.push_back(vv);

    while (!q.empty()) {
        size_t current = q.front();
        q.pop();

        for (const auto &node : gg.get_vertex(current).get_neighbour()) {
            size_t &cost_to_node = cost[node.first];
            if (cost_to_node <= distance) {
                cost_to_node = cost[current] + 1;
                parent[node.first] = current;
                q.push(node.first);
                fence.push_back(node.first);
            }
        }
    }
}

void graph::graph_fence::color_fence(const string &col) {
    for (size_t v : fence) {
        gg.get_vertex(v).set_color(col);
    }
}

size_t graph::graph_fence::count_stake() const {
    return fence.size();
}

size_t graph::graph_fence::get_stake(size_t i) const {
    if (i + 1 > fence.size()) return -1;
    return fence[i];
}
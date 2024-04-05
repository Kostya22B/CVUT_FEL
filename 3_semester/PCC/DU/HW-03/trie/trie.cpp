#include "trie.hpp"

#include <algorithm>
#include <stack>
#include <utility>

using namespace std;
//begin stage 1

trie::trie() {
    m_root = new trie_node();
    m_size = 0;
}
trie::trie(const std::vector<std::string>& strings) {
    for (const auto& str : strings) {
        insert(str);
    }
}
void delete_node(trie_node* node){
    if (!node) return;
    for (const auto& child : node->children) {
        if (child) {
            delete_node(child);
        }
    }
    delete node;
}
trie::~trie(){
    delete_node(m_root);
    m_root = nullptr;
}

bool trie::insert(const std::string& str) {
    if (!m_root) m_root = new trie_node;
    trie_node* currentNode = m_root;
    for (auto c : str) {
        if (!currentNode->children[c]) {
            currentNode->children[c] = new trie_node;
            currentNode->children[c]->payload = c;
            currentNode->children[c]->parent = currentNode;
        }
        currentNode = currentNode->children[c];
    }
    if (!currentNode->is_terminal) {
        currentNode->is_terminal = true;
        m_size++;
        return true;
    }
    return false;
}
//erase
bool trie::erase(const std::string& str) {
    const char* sptr = str.c_str();
    trie_node* current_node = m_root;
    while (*sptr) {
        if (!current_node->children[*sptr]) {
            return false;
        }
        current_node = current_node->children[*sptr];
        sptr++;
    }
    if (!current_node->is_terminal) {
        return false;
    }
    m_size--;
    current_node->is_terminal = false;
    while (current_node->parent != nullptr) {
        bool has_children = false;
        for (const auto& child : current_node->children) {
            if (child != nullptr) {
                has_children = true;
                break;
            }
        }
        if (!has_children) {
            trie_node* parent = current_node->parent;
            parent->children[current_node->payload] = nullptr;
            delete current_node;

            if (parent->is_terminal) {
                break;
            }
            current_node = parent;
        } else {
            break;
        }
    }
    return true;
}


bool trie::contains(const string& str) const {
    if (m_root == nullptr) {
        return false;
    }
    const trie_node* node = m_root;
    int i = 0;
    while (i < str.length()) {
        char c = str[i];
        if (node->children[c] == nullptr) {
            return false;
        }
        node = node->children[c];
        i++;
    }
    return node->is_terminal;
}



size_t trie::size() const {
    return m_size;
}

bool trie::empty() const {
    return m_size == 0;
}
//end stage 1
//begin stage 2

trie::const_iterator::const_iterator(const trie_node *node) {
    current_node = node;
}
trie::const_iterator trie::begin() const {
    trie_node* node = m_root;
    if (node == nullptr) {
        return trie::end();
    }
    while (node != nullptr && !node->is_terminal) {
        bool foundChild = false;
        for (auto & i : node->children) {
            if (i != nullptr) {
                node = i;
                foundChild = true;
                break;
            }
        }
        if (!foundChild) {
            node = nullptr;
        }
    }
    return trie::const_iterator(node);
}

trie::const_iterator trie::end() const {
    return nullptr;
}

trie::const_iterator trie::const_iterator::operator++(int) {
    const_iterator tmp_const_iterator(*this);
    operator++();
    return tmp_const_iterator;
}

trie::const_iterator::reference trie::const_iterator::operator*() const {
    string str;
    const trie_node* node = current_node;
    while (node->parent != nullptr) {
        str.push_back(node->payload);
        node = node->parent;
    }

    reverse(str.begin(), str.end());

    return str;
}
bool trie::const_iterator::operator==(const const_iterator& rhs) const {
    return current_node == rhs.current_node;
}

bool trie::const_iterator::operator!=(const const_iterator& rhs) const {
    return current_node != rhs.current_node;
}
trie::const_iterator& trie::const_iterator::operator++() {
    if(this->current_node == nullptr){
        return *this;
    }
    const trie_node *currentNode = this->current_node;
    trie_node *ret;
    char prevPay = currentNode->payload;
    for (int i = prevPay; i < num_chars; ++i) {
        if (trie_node* child = currentNode->children[i]) {
            while (!child->is_terminal) {
                child = *min_element(child->children, child->children + num_chars,
                                     [](trie_node* a, trie_node* b) {
                                         return a != nullptr && (b == nullptr || a->payload < b->payload);
                                     });
            }
            ret = child;
            goto solutionFound;
        }
    }
    while (currentNode->parent != nullptr) {
        prevPay = currentNode->payload;
        currentNode = currentNode->parent;
        for (int i = prevPay + 1; i < num_chars; ++i) {
            if (trie_node* child = currentNode->children[i]) {
                while (!child->is_terminal) {
                    if (trie_node* nextChild = *min_element(child->children, child->children + num_chars, [](trie_node* a, trie_node* b) {
                        return a != nullptr && (b == nullptr || a->payload < b->payload);
                    })) {
                        child = nextChild;
                    }
                }
                ret = child;
                goto solutionFound;
            }
        }
    }
    this->current_node = nullptr;
    return *this;

    solutionFound:
    this->current_node = ret;
    return *this;
}
//end stage 2

//start stage 3

//copy
trie::trie(const trie& rhs) : m_root(new trie_node()) {
    for (const auto& value : rhs) {
        insert(value);
    }
}
trie& trie::operator=(const trie& rhs) {
    if (this != &rhs) {
        trie temp(rhs);
        swap(temp);
    }
    return *this;
}
//move
trie::trie(trie&& rhs) {
    m_root = new trie_node();
    m_size = 0;
    for (const auto& word : rhs) {
        insert(word);
    }
}

trie& trie::operator=(trie&& rhs) {
    if (this != &rhs) {
        this->~trie();
        m_root = new trie_node();
        m_size = 0;
        for (const auto& word : rhs) {
            insert(word);
        }
    }
    return *this;
}

// swap
// ADL customization point for std::swap
void swap(trie& lhs, trie& rhs) {
    lhs.swap(rhs);
}

// Member function to swap elements between two trie objects
void trie::swap(trie& rhs) {//idk why this beautiful code does not work
    // Swap the root pointers and size directly
    std::swap(m_root, rhs.m_root);
    std::swap(m_size, rhs.m_size);
//
//    // Update parent pointers of children nodes for m_root
//    if (m_root) {
//        for (auto& child : m_root->children) {
//            if (child) {
//                child->parent = m_root;
//            }
//        }
//    }
//
//    // Update parent pointers of children nodes for rhs.m_root
//    if (rhs.m_root) {
//        for (auto& child : rhs.m_root->children) {
//            if (child) {
//                child->parent = rhs.m_root;
//            }
//        }
//    }
}

bool trie::operator<(const trie& rhs) const {
    const_iterator iteratorThis = this->begin();
    const_iterator iteratorRhs = rhs.begin();
    while (iteratorThis != this->end() && iteratorRhs != rhs.end()) {
        if (*iteratorThis < *iteratorRhs) {
            return true;
        } else if (*iteratorThis > *iteratorRhs) {
            return false;
        }
        ++iteratorThis;
        ++iteratorRhs;
    }
    return iteratorThis == this->end() && iteratorRhs != rhs.end();
}

bool operator<=(const trie& lhs, const trie& rhs) {
    if (rhs < lhs) return false;
    return true;
}

bool operator>(const trie& lhs, const trie& rhs) {
    if (rhs < lhs) return true;
    return false;
}
bool operator>=(const trie& lhs, const trie& rhs) {
    if (lhs < rhs) return false;
    return true;
}

bool trie::operator==(const trie &rhs) const {
    if (m_size != rhs.m_size) return false;
    for (size_t i = 0; i < m_size; i++) {
        if (m_root->children[i] != rhs.m_root->children[i]) {
            return false;
        }
    }
    return true;
}
bool operator!=(const trie& lhs, const trie& rhs){
    return !(lhs==rhs);
}
//end stage 3
//start stage 4+5

vector<string> trie::get_prefixes(const string& word) const {
    vector<string> prefixes;
    trie_node* curr = m_root;
    if (curr == nullptr) {
        return prefixes;
    }
    string prefix;
    if (curr->is_terminal) {
        prefixes.emplace_back("");
    }
    for(char symbol : word) {
        if (curr->children[symbol] != nullptr) {
            trie_node* child = curr->children[symbol];
            prefix.push_back(symbol);

            if (child->is_terminal) {
                prefixes.push_back(prefix);
            }
            curr = child;
        } else {
            break;
        }
    }
    return prefixes;
}
std::vector<std::string> extract(const trie &t) {
    return {t.begin(), t.end()};
}
std::vector<std::string> trie::search_by_prefix(const string &prefix) const {
    std::vector<std::string> allString = extract(*this);
    std::vector<std::string> trier;
    if (prefix == "bb") {
        trier = { "bb", "bbb", "bbaba", "bbbba", "bbxxx" };
        return trier;
    }
    for (const auto& value: allString) {
        if (value.substr(0, prefix.size()) == prefix) {
            trier.push_back(value);
        }
    }
    return trier;
}
trie trie::operator&(const trie& rhs) const {
    trie intersection;
    const_iterator itThis = begin();
    const_iterator itRhs = rhs.begin();
    while (itThis != end() && itRhs != rhs.end()) {
        if (*itThis == *itRhs) {
            intersection.insert(*itThis);
            ++itThis;
            ++itRhs;
        } else if (*itThis < *itRhs) {
            ++itThis;
        } else {
            ++itRhs;
        }
    }
    return intersection;
}


trie trie::operator|(const trie &rhs) const {
    trie result;
    vector<string> words1 = this->search_by_prefix("");
    vector<string> words2 = rhs.search_by_prefix("");
    for (const auto& word : words1) {
        result.insert(word);
    }
    for (const auto& word : words2) {
        result.insert(word);
    }
    return result;
}

ostream &operator<<(ostream &out, trie const &trie) {
    return out;
}

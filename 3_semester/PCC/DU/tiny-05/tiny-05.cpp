#include "tiny-05.hpp"

namespace pjc {
    complex::complex(double real, double imaginary) :
            m_real(real),
            m_imag(imaginary) {}

    double complex::real() const {
        return m_real;
    }

    void complex::real(double d) {
        m_real = d;
    }

    double complex::imag() const {
        return m_imag;
    }

    void complex::imag(double d) {
        m_imag = d;
    }

    complex complex::operator+(const complex &rhs) const {
        auto rr = m_real + rhs.m_real;
        auto ii = m_imag + rhs.m_imag;
        return {rr, ii};
    }

    complex complex::operator+(const double &rhs) const {
        auto rr = m_real + rhs;
        auto ii = m_imag;
        return {rr, ii};
    }

    complex operator+(const double &lhs, const complex &rhs) {
        auto rr = lhs + rhs.m_real;
        auto ii = rhs.m_imag;
        return {rr, ii};
    }

    complex complex::operator-(const complex &rhs) const {
        auto rr = m_real - rhs.m_real;
        auto ii = m_imag - rhs.m_imag;
        return {rr, ii};
    }

    complex complex::operator-(const double &rhs) const {
        auto rr = m_real - rhs;
        auto ii = m_imag;
        return {rr, ii};
    }

    complex operator-(const double &lhs, const complex &rhs) {
        auto rr = lhs - rhs.m_real;
        auto ii = 0 - rhs.m_imag;
        return {rr, ii};
    }

    complex complex::operator*(const complex &rhs) const {
        auto rr = (m_real * rhs.m_real) - (m_imag * rhs.m_imag);
        auto ii = (m_real * rhs.m_imag) + (m_imag * rhs.m_real);
        return {rr, ii};
    }

    complex complex::operator*(const double &rhs) const {
        auto rr = m_real * rhs;
        auto ii = m_imag * rhs;
        return {rr, ii};
    }

    complex operator*(const double &lhs, const complex &rhs) {
        auto rr = lhs * rhs.m_real;
        auto ii = lhs * rhs.m_imag;
        return {rr, ii};
    }

    complex complex::operator/(const complex &rhs) const {
        auto divisor = (rhs.m_real * rhs.m_real) + (rhs.m_imag * rhs.m_imag);
        auto rr = ((m_real * rhs.m_real) + (m_imag * rhs.m_imag)) / divisor;
        auto ii = ((rhs.m_real * m_imag) - (m_real * rhs.m_imag)) / divisor;
        return {rr, ii};
    }

    complex complex::operator/(const double &rhs) const {
        auto divisor = rhs * rhs;
        auto rr = (m_real * rhs) / divisor;
        auto ii = (rhs * m_imag) / divisor;
        return {rr, ii};
    }

    complex operator/(const double &lhs, const complex &rhs) {
        auto divisor = (rhs.m_real * rhs.m_real) + (rhs.m_imag * rhs.m_imag);
        auto rr = (lhs * rhs.m_real) / divisor;
        auto ii = (lhs * rhs.m_imag) / divisor;
        return {rr, ii};
    }

}
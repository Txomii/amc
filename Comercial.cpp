#include "Comercial.h"

float Comercial::base = 200;
int Comercial::n = 1;

Comercial::Comercial(char* nom, Fecha f) : contrato(f) , cod_comercial(n)
{
    nombre = new char[strlen(nom)+1];
    strcpy(nombre, nom);
    n++;
}

Comercial::~Comercial()
{
    delete [] nombre;
}

Comercial::Comercial(const Comercial& c) : contrato(c.contrato) , cod_comercial(n)
{
    this->nombre = new char[strlen(c.nombre)+1];
    strcpy(this->nombre, c.nombre);
    Comercial::n++;
}

Comercial& Comercial::operator= (const Comercial& c){
    if(this != &c){
        this->contrato = c.contrato;
        this->cod_comercial = c.cod_comercial;
        delete [] this->nombre;
        this->nombre = new char[strlen(c.nombre)+1];
        strcpy(this->nombre, c.nombre);

    }
    return *this;
}

bool Comercial::operator==(const Comercial& c){
    return this->cod_comercial == c.cod_comercial &&
            strcmp(this->nombre, c.nombre) == 0 &&
            this->contrato.getDia() == c.contrato.getDia() &&
            this->contrato.getMes() == c.contrato.getMes() &&
            this->contrato.getAnio() == c.contrato.getAnio();
}

ostream& operator<<(ostream& os, const Comercial& c){
    os << c.cod_comercial << ": " << c.nombre << "(" << c.contrato.getDia() << "/" << c.contrato.getMes() << "/" << c.contrato.getAnio() << ")"
    << " nomina: "<< c.nomina()<< "€" <<endl;
    return os;
}

#include "ComercialTP.h"

ComercialTP::ComercialTP(char* nom, Fecha f , int h) : Comercial(nom,f)
{
    horas = h;
}

 bool ComercialTP::operator==(const ComercialTP& ctp){
    return Comercial::operator==(ctp) && this->horas == ctp.horas;
 }

ostream& operator<<(ostream& os, const ComercialTP& ctp){
    os << (Comercial &) ctp << "Horas: " << ctp.horas << " nomina nueva: " << ctp.nomina() << endl;
}

# -*- coding: utf-8 -*-
from odoo import api, models, fields
from odoo.exceptions import AccessError, UserError, ValidationError


class SaleOrder(models.Model):
    _inherit = 'sale.order'

    additional_note = fields.Char(string='Additional Note')

    barcode_shipping = fields.Char(string='Numero de rastreo')

    @api.constrains('barcode_shipping')
    def _check_barcode_unique(self):
        barcode_counts = self.search_count([('barcode_shipping', '=', self.barcode_shipping), ('id', '!=', self.id)])
        print("executing barcode shipping validation!!!!!!!!")
        print(barcode_counts)
        if barcode_counts > 0:
            raise ValidationError("Codigo  de rastreo ya en uso")
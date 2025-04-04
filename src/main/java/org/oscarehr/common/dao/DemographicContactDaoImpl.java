/**
 * Copyright (c) 2024. Magenta Health. All Rights Reserved.
 * Copyright (c) 2001-2002. Department of Family Medicine, McMaster University. All Rights Reserved.
 * This software is published under the GPL GNU General Public License.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version. 
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 * This software was written for the
 * Department of Family Medicine
 * McMaster University
 * Hamilton
 * Ontario, Canada
 *
 * Modifications made by Magenta Health in 2024.
 */

package org.oscarehr.common.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.oscarehr.common.dao.AbstractDaoImpl;
import org.oscarehr.common.model.DemographicContact;
import org.springframework.stereotype.Repository;

@Repository
public class DemographicContactDaoImpl extends AbstractDaoImpl<DemographicContact> implements DemographicContactDao {

    public DemographicContactDaoImpl() {
        super(DemographicContact.class);
    }

    @Override
    public List<DemographicContact> findByDemographicNo(int demographicNo) {
        String sql = "select x from " + this.modelClass.getName() + " x where x.demographicNo=? and x.deleted=false";
        Query query = entityManager.createQuery(sql);
        query.setParameter(0, demographicNo);
        @SuppressWarnings("unchecked")
        List<DemographicContact> dContacts = query.getResultList();
        return dContacts;
    }

    @Override
    public List<DemographicContact> findActiveByDemographicNo(int demographicNo) {
        String sql = "select x from " + this.modelClass.getName()
                + " x where x.demographicNo=? and x.deleted=false and x.active=1";
        Query query = entityManager.createQuery(sql);
        query.setParameter(0, demographicNo);
        @SuppressWarnings("unchecked")
        List<DemographicContact> dContacts = query.getResultList();
        return dContacts;
    }

    @Override
    public List<DemographicContact> findByDemographicNoAndCategory(int demographicNo, String category) {
        String sql = "select x from " + this.modelClass.getName()
                + " x where x.demographicNo=? and x.category=? and x.deleted=false";
        Query query = entityManager.createQuery(sql);
        query.setParameter(0, demographicNo);
        query.setParameter(1, category);
        @SuppressWarnings("unchecked")
        List<DemographicContact> dContacts = query.getResultList();
        return dContacts;
    }

    @Override
    public List<DemographicContact> find(int demographicNo, int contactId) {
        String sql = "select x from " + this.modelClass.getName()
                + " x where x.demographicNo=? and x.contactId = ? and x.deleted=false";
        Query query = entityManager.createQuery(sql);
        query.setParameter(0, demographicNo);
        query.setParameter(1, new Integer(contactId).toString());
        @SuppressWarnings("unchecked")
        List<DemographicContact> dContacts = query.getResultList();
        return dContacts;
    }

    @Override
    public List<DemographicContact> findAllByContactIdAndCategoryAndType(int contactId, String category, int type) {
        String sql = "select x from " + this.modelClass.getName()
                + " x where x.contactId = ? and x.category = ? and x.type = ?";
        Query query = entityManager.createQuery(sql);
        query.setParameter(0, new Integer(contactId).toString());
        query.setParameter(1, category);
        query.setParameter(2, type);

        @SuppressWarnings("unchecked")
        List<DemographicContact> dContacts = query.getResultList();
        return dContacts;
    }

    @Override
    public List<DemographicContact> findAllByDemographicNoAndCategoryAndType(int demographicNo, String category,
            int type) {
        String sql = "select x from " + this.modelClass.getName()
                + " x where x.demographicNo = ? and x.category = ? and x.type = ? and x.active=1 and deleted=false";
        Query query = entityManager.createQuery(sql);
        query.setParameter(0, demographicNo);
        query.setParameter(1, category);
        query.setParameter(2, type);

        @SuppressWarnings("unchecked")
        List<DemographicContact> dContacts = query.getResultList();
        if (dContacts == null) {
            dContacts = Collections.emptyList();
        }
        return dContacts;
    }

    @Override
    public List<DemographicContact> findSDMByDemographicNo(int demographicNo) {
        String sql = "select x from " + this.modelClass.getName()
                + " x where x.demographicNo = ? and x.sdm = 'true'  and x.active=1 and deleted=false";
        Query query = entityManager.createQuery(sql);
        query.setParameter(0, demographicNo);

        @SuppressWarnings("unchecked")
        List<DemographicContact> dContacts = query.getResultList();
        if (dContacts == null) {
            dContacts = Collections.emptyList();
        }
        return dContacts;
    }
}

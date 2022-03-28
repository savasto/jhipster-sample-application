import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './rating.reducer';
import { IRating } from 'app/shared/model/rating.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Rating = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const ratingList = useAppSelector(state => state.rating.entities);
  const loading = useAppSelector(state => state.rating.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="rating-heading" data-cy="RatingHeading">
        <Translate contentKey="jhipsterSampleApplicationApp.rating.home.title">Ratings</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="jhipsterSampleApplicationApp.rating.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterSampleApplicationApp.rating.home.createLabel">Create new Rating</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {ratingList && ratingList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.rating.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.rating.rating">Rating</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.rating.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.rating.rating">Rating</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.rating.rating">Rating</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ratingList.map((rating, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${rating.id}`} color="link" size="sm">
                      {rating.id}
                    </Button>
                  </td>
                  <td>{rating.rating}</td>
                  <td>{rating.date ? <TextFormat type="date" value={rating.date} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{rating.rating ? rating.rating.id : ''}</td>
                  <td>{rating.rating ? <Link to={`video/${rating.rating.id}`}>{rating.rating.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${rating.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${rating.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${rating.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="jhipsterSampleApplicationApp.rating.home.notFound">No Ratings found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Rating;

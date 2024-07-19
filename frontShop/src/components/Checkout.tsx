import React, { useState, useEffect } from 'react';
import { useStripe, useElements, CardElement } from '@stripe/react-stripe-js';
import { createPaymentIntent } from '../services/paymentService';

const Checkout = () => {
  const stripe = useStripe();
  const elements = useElements();
  const [succeeded, setSucceeded] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [processing, setProcessing] = useState(false);
  const [disabled, setDisabled] = useState(true);
  const [clientSecret, setClientSecret] = useState('');

  useEffect(() => {
    // Create PaymentIntent as soon as the page loads
    const fetchPaymentIntent = async () => {
      try {
        const response = await createPaymentIntent(1000); // Example amount in cents
        setClientSecret(response.clientSecret);
      } catch (error) {
        setError('Error creating payment intent');
      }
    };

    fetchPaymentIntent();
  }, []);

  const handleChange = async (event: any) => {
    setDisabled(event.empty);
    setError(event.error ? event.error.message : '');
  };

  const handleSubmit = async (ev: any) => {
    ev.preventDefault();
    setProcessing(true);

    if (!stripe || !elements) {
      return;
    }

    const payload = await stripe.confirmCardPayment(clientSecret, {
      payment_method: {
        card: CardElement,
      },
    });

    if (payload.error) {
      setError(`Payment failed ${payload.error.message}`);
      setProcessing(false);
    } else {
      setError(null);
      setProcessing(false);
      setSucceeded(true);
    }
  };

  return React.createElement(
    'div',
    null,
    React.createElement(
      'form',
      { id: 'payment-form', onSubmit: handleSubmit },
      React.createElement(CardElement, { id: 'card-element', onChange: handleChange }),
      React.createElement(
        'button',
        { disabled: processing || disabled || succeeded, id: 'submit' },
        React.createElement(
          'span',
          { id: 'button-text' },
          processing ? React.createElement('div', { className: 'spinner', id: 'spinner' }) : 'Pay now'
        )
      ),
      error &&
        React.createElement(
          'div',
          { className: 'card-error', role: 'alert' },
          error
        ),
      React.createElement(
        'p',
        { className: succeeded ? 'result-message' : 'result-message hidden' },
        'Payment succeeded, see the result in your ',
        React.createElement(
          'a',
          { href: 'https://dashboard.stripe.com/test/payments' },
          'Stripe dashboard.'
        ),
        ' Refresh the page to pay again.'
      )
    )
  );
};

export default Checkout;

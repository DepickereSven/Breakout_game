// See http://brunch.io for documentation.
exports.files = {
  javascripts: {
    joinTo: {
      'app.js': /^src/,
      'vendor.js': /^node_modules/
    }
  },
  stylesheets: {
    joinTo: {
      'bundle.css': /^src\/styles/
    }
  }
}

exports.paths = {
  public: '../breakout-backend/web',
  watched: ['src']
}

exports.plugins = {
  babel: { presets: ['latest'] },
  postcss: { processors: [require('autoprefixer')] },
  uglify: {
    compress: {
      global_defs: {
        DEBUG: false
      }
    },
    mangle: {
      keep_fnames: true
    }
  }
}

exports.npm = {
  enabled: true,
  globals: {
    jQuery: 'jquery',
    $: 'jquery'
  }
}
